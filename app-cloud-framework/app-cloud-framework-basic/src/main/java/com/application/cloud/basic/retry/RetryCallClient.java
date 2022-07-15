package com.application.cloud.basic.retry;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author ：admin
 * @date ：2022-7-15
 * @description: 重试客户端
 * https://juejin.cn/post/6844903653644451853
 * @modified By：
 * @version: 1.0.0
 */
public class RetryCallClient {
	
	/**
	 * 到期线程池对象.
	 */
	private static ThreadLocal<Integer> retryTimesInThread = new ThreadLocal<>();
	
	/**
	 * 设置当前方法重试次数
	 *
	 * @param retryCount
	 * @return
	 */
	public RetryCallClient(Integer retryCount) {
		if (retryTimesInThread.get() == null) {
			retryTimesInThread.set(retryCount);
		}
	}
	
	/**
	 * 重试当前方法
	 * <p>按顺序传入调用者方法的所有参数</p>
	 *
	 * @param args
	 * @return
	 */
	public Object retry(Object... args) {
		try {
			Integer retryCount = retryTimesInThread.get();
			if (Objects.isNull(retryCount) || retryCount <= 0) {
				retryTimesInThread.remove();
				return null;
			}
			retryTimesInThread.set(--retryCount);
			String upperClassName = Thread.currentThread().getStackTrace()[2].getClassName();
			String upperMethodName = Thread.currentThread().getStackTrace()[2].getMethodName();
			//反射机制.
			Class clazz = Class.forName(upperClassName);
			Object targetObject = clazz.newInstance();
			Method targetMethod = null;
			for (Method method : clazz.getDeclaredMethods()) {
				if (method.getName().equals(upperMethodName)) {
					targetMethod = method;
					break;
				}
			}
			if (targetMethod == null) {
				return null;
			}
			targetMethod.setAccessible(true);
			return targetMethod.invoke(targetObject, args);
		} catch (Exception e) {
			System.err.println("重试方式出错了,错误信息是：" + e.getMessage());
			return null;
		}
	}
}
