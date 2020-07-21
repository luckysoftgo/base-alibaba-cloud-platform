package com.application.cloud.auth.integration.auth.wechat;

import lombok.Data;

/**
 * 微信授权返回的信息
 * @author : 孤狼
 * @NAME: WechatAppToken
 * @DESC: WechatAppToken 类设计
 **/
@Data
public class WechatAppToken {

    private String openId;
    private String unionId;
    private String sessionKey;
    private String username;
    private String encryptedData;
    private String iv;

    public WechatAppToken(String openId, String unionId, String sessionKey) {
        this.openId = openId;
        this.unionId = unionId;
        this.sessionKey = sessionKey;
    }
}
