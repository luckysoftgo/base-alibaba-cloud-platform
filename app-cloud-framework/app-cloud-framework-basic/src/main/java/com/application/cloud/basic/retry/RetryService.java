package com.application.cloud.basic.retry;

/**
 * @author ：admin
 * @date ：2022-7-15
 * @description: 重试接口定义
 * @modified By：
 * @version: 1.0.0
 */
public interface RetryService<T> {
	
	/**
	 * 番红花结果
	 *
	 * @return
	 * @throws Exception
	 */
	T run() throws Exception;
	
	/**
	 * 最终失败结果
	 *
	 * @param e
	 */
	public T retryFailed(Throwable e);
	
	/**
	 * 快照信息
	 *
	 * @return
	 */
	default T snapshot() {
		return null;
	}
	
}
