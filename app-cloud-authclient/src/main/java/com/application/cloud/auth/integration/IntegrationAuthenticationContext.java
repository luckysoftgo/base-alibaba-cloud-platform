package com.application.cloud.auth.integration;

import com.application.cloud.auth.integration.dto.IntegrationAuthentication;

/**
 * @author : 孤狼
 * @NAME: IntegrationAuthenticationContext
 * @DESC: IntegrationAuthenticationContext类设计
 **/
public class IntegrationAuthenticationContext {
	
	private static ThreadLocal<IntegrationAuthentication> holder = new ThreadLocal<>();
	
	public static void set(IntegrationAuthentication integrationAuthentication){
		holder.set(integrationAuthentication);
	}
	
	public static IntegrationAuthentication get(){
		return holder.get();
	}
	
	public static void clear(){
		holder.remove();
	}
	
}
