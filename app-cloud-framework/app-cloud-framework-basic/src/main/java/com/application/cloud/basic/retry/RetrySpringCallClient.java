package com.application.cloud.basic.retry;

import com.alibaba.fastjson.JSON;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * @author ：孤狼
 * @date ：2022-7-15
 * @description: 请求重试机制
 * @modified By：
 * @version: 1.0.0
 */
public class RetrySpringCallClient<T> {
	/**
	 * 默认次数
	 */
	private static int DEFAULT_RETRY_COUNT = 3;
	/**
	 * 默认间隔
	 */
	private final static int[] DEFAULT_DELAY_SECONDS = {3, 5, 10, 15, 20, 30};
	/**
	 * 任务队列
	 */
	private static Queue<RetryCallable> TASK_QUEUE = new ConcurrentLinkedQueue<>();
	/**
	 * 执行线程池
	 */
	public static ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	public static ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
	/**
	 * 计数器
	 */
	public static CountDownLatch countDownLatch = null;
	
	static {
		
		//配置核心线程数
		executor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
		//配置最大线程数
		executor.setMaxPoolSize(Runtime.getRuntime().availableProcessors() * 2);
		//配置队列大小
		executor.setQueueCapacity(20);
		//配置线程池中的线程的名称前缀
		executor.setThreadNamePrefix("async_");
		// rejection-policy：当pool已经达到max size的时候，如何处理新任务
		// CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		//用来设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean
		executor.setWaitForTasksToCompleteOnShutdown(true);
		//允许核心线程超时
		executor.setAllowCoreThreadTimeOut(true);
		//该方法用来设置线程池中任务的等待时间、秒 ，如果超过这个时候还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住。
		executor.setAwaitTerminationSeconds(60);
		//保持线程正常链接秒
		executor.setKeepAliveSeconds(30);
		//执行初始化
		executor.initialize();
		
		//线程池名称
		scheduler.setThreadNamePrefix("scheduler_");
		scheduler.setPoolSize(Runtime.getRuntime().availableProcessors() * 2);
		scheduler.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		scheduler.initialize();
	}
	
	
	/**
	 * 重试客户端
	 *
	 * @param retryCount
	 */
	public RetrySpringCallClient(Integer retryCount) {
		if (Objects.isNull(retryCount) || retryCount == 0) {
			retryCount = DEFAULT_RETRY_COUNT;
		} else {
			DEFAULT_RETRY_COUNT = retryCount;
		}
		countDownLatch = new CountDownLatch(retryCount);
		//每秒检查一次：遍历任务队列，如需执行，线程池调度执行
		scheduler.scheduleAtFixedRate(() -> {
			for (RetryCallable task : TASK_QUEUE) {
				long nextRetryMillis = task.nextRetryMillis;
				if (nextRetryMillis != -1 && nextRetryMillis <= System.currentTimeMillis()) {
					task.nextRetryMillis = -1;
					executor.submit(task);
				}
			}
		}, 1000);
	}
	
	/**
	 * 重试
	 *
	 * @param task
	 */
	public <T> Future<T> doRetry(RetryService task) {
		return doRetry(DEFAULT_RETRY_COUNT, DEFAULT_DELAY_SECONDS, task);
	}
	
	/**
	 * 重试
	 *
	 * @param task
	 */
	public <T> Future<T> doRetry(int maxRetryTime, RetryService task) {
		return doRetry(maxRetryTime, DEFAULT_DELAY_SECONDS, task);
	}
	
	/**
	 * 重试
	 *
	 * @param task
	 */
	public <T> Future<T> doRetry(int[] retryDelaySeconds, RetryService task) {
		return doRetry(retryDelaySeconds.length, retryDelaySeconds, task);
	}
	
	/**
	 * @param maxRetryTime      最大重试次数
	 * @param retryDelaySeconds 重试间隔时间数组
	 * @param task              任务
	 */
	public <T> Future<T> doRetry(final int maxRetryTime, final int[] retryDelaySeconds, final RetryService task) {
		Callable callable = new RetryCallable(maxRetryTime, retryDelaySeconds, task);
		return executor.submit(callable);
	}
	
	/**
	 * 关闭线程池资源.
	 *
	 * @return
	 */
	public boolean close() {
		try {
			//等待所有线程执行完成.
			countDownLatch.await();
			executor.shutdown();
			TASK_QUEUE.clear();
			executor.destroy();
			scheduler.destroy();
			return true;
		} catch (Exception e) {
			System.err.println("关闭线程池异常,异常信息是:" + e.getMessage());
			return false;
		}
	}
	
	/**
	 * 自定义线程类
	 */
	private static class RetryCallable implements Callable {
		private final RetryService task;
		private final int maxRetryTimes;
		private final int[] retryDelaySeconds;
		private int retryTimes;
		private volatile long nextRetryMillis;
		
		//构造函数
		public RetryCallable(final int maxRetryTimes, final int[] retryDelaySeconds, final RetryService task) {
			this.task = task;
			if (maxRetryTimes <= 0) {
				this.maxRetryTimes = DEFAULT_RETRY_COUNT;
			} else {
				this.maxRetryTimes = maxRetryTimes;
			}
			if (retryDelaySeconds == null || retryDelaySeconds.length == 0) {
				this.retryDelaySeconds = DEFAULT_DELAY_SECONDS;
			} else {
				this.retryDelaySeconds = retryDelaySeconds;
			}
		}
		
		//执行业务方法
		@Override
		public Object call() throws Exception {
			countDownLatch.countDown();
			try {
				return task.run();
			} catch (Throwable e) {
				int sleepSeconds = retryTimes < retryDelaySeconds.length ? retryDelaySeconds[retryTimes] : retryDelaySeconds[retryDelaySeconds.length - 1];
				if (retryTimes < maxRetryTimes) {
					if (retryTimes == 0) {
						TASK_QUEUE.add(this);
						System.out.println("task executed error, " + sleepSeconds + " seconds do next... " + e.getMessage());
					} else {
						System.out.println("retry " + retryTimes + " times error, " + sleepSeconds + " seconds do next... " + e.getMessage());
					}
					nextRetryMillis = System.currentTimeMillis() + sleepSeconds * 1000;
				} else {
					System.out.println("retry " + retryTimes + " times error" + e.getMessage());
					System.out.println("retry snapshot: " + JSON.toJSONString(task.snapshot()));
					TASK_QUEUE.remove(this);
					task.retryFailed(e);
				}
				retryTimes++;
			}
			return null;
		}
	}
}
