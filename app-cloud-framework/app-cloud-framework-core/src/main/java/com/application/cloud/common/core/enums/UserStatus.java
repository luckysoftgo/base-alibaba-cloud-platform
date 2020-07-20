package com.application.cloud.common.core.enums;

/**
 * 用户状态
 * 
 * @author cloud
 */
public enum UserStatus{
	
    OK("0", "正常"),
	DISABLE("1", "停用"),
	DELETED("2", "删除"),
	LOCKED("3", "锁定"),
	;
	/**
	 * 状态值
	 */
	private final String code;
	/**
	 * 描述
	 */
    private final String info;

    UserStatus(String code, String info){
        this.code = code;
        this.info = info;
    }

    public String getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }
}
