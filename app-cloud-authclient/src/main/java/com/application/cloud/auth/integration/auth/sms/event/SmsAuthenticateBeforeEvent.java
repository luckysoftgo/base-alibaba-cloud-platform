package com.application.cloud.auth.integration.auth.sms.event;

import org.springframework.context.ApplicationEvent;

/**
 * 短信认证之前的事件，可以监听事件进行用户手机号自动注册
 * @author : 孤狼
 * @NAME: SmsAuthenticateBeforeEvent
 * @DESC: SmsAuthenticateBeforeEvent 类设计
 **/
public class SmsAuthenticateBeforeEvent extends ApplicationEvent {
	
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public SmsAuthenticateBeforeEvent(Object source) {
		super(source);
    }
}
