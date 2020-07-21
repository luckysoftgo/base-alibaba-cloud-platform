package com.application.cloud.auth.config;

import com.alibaba.fastjson.JSON;
import com.application.cloud.auth.exception.CustomOauthExceptionSerializer;
import com.application.cloud.auth.integration.IntegrationAuthenticationFilter;
import com.application.cloud.auth.integration.IntegrationUserDetailsService;
import com.application.cloud.common.core.constant.CacheConstants;
import com.application.cloud.common.core.constant.SecurityConstants;
import com.application.cloud.common.security.domain.LoginUser;
import com.application.cloud.common.security.service.RedisClientDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * OAuth2 认证服务配置
 * 
 * @author cloud
 */
@Slf4j
@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter{
	
	/**
	 * 认证管理器.
	 */
    @Autowired
    private AuthenticationManager authenticationManager;
	@Autowired
	private IntegrationUserDetailsService integrationUserDetailsService;
	@Autowired
	private CustomOauthExceptionSerializer customOauthExceptionSerializer;
	@Autowired
	private IntegrationAuthenticationFilter integrationAuthenticationFilter;
	
	/**
	 * 基于缓存的认证
	 */
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    @Autowired
    private TokenEnhancer tokenEnhancer;
    /**
	 * 基于数据看的认证
	 */
	@Autowired
	private DataSource dataSource;

    /**
     * 定义授权和令牌端点以及令牌服务
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints){
        endpoints
                // 请求方式GET ,POST
                .allowedTokenEndpointRequestMethods(HttpMethod.GET,HttpMethod.POST)
                // 指定token存储位置
                .tokenStore(tokenStore())
                // 自定义生成令牌
                .tokenEnhancer(tokenEnhancer)
                // 指定认证管理器
                .authenticationManager(authenticationManager)
                // 是否重复使用 refresh_token
                .reuseRefreshTokens(false)
		        //集成的认证方式指定.
		        .userDetailsService(integrationUserDetailsService)
                // 自定义异常处理
                .exceptionTranslator(customOauthExceptionSerializer);
    }

    /**
     * 配置令牌端点(Token Endpoint)的安全约束
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer){
	    oauthServer
			    //运行表单进行验证.
			    .allowFormAuthenticationForClients()
			    //allow check token
			    .tokenKeyAccess("isAuthenticated()")
			    .checkTokenAccess("permitAll()")
			    .addTokenEndpointAuthenticationFilter(integrationAuthenticationFilter);
    }

    /**
     * 声明 ClientDetails 实现
     */
    public RedisClientDetailsService clientDetailsService(){
        RedisClientDetailsService clientDetailsService = new RedisClientDetailsService(dataSource);
        return clientDetailsService;
    }

    /**
     * 配置客户端请求的设置详情
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
        clients.withClientDetails(clientDetailsService());
    }

    /**
     * 基于 Redis 实现，令牌保存到缓存
     */
    @Bean
    public TokenStore tokenStore(){
        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        tokenStore.setPrefix(CacheConstants.OAUTH_ACCESS);
        return tokenStore;
    }

    /**
     * 自定义生成令牌
     */
    @Bean
    public TokenEnhancer tokenEnhancer(){
        return (accessToken, authentication) -> {
            if (authentication.getUserAuthentication() != null){
                Map<String, Object> additionalInformation = new LinkedHashMap<String, Object>();
                //自定义生成的令牌桶信息.
                LoginUser user = (LoginUser) authentication.getUserAuthentication().getPrincipal();
                additionalInformation.put(SecurityConstants.DETAILS_USER_ID, user.getUserId());
                additionalInformation.put(SecurityConstants.DETAILS_USERNAME, user.getUsername());
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
            }
            log.info("自定义令牌信息是:{}",JSON.toJSONString(accessToken));
            return accessToken;
        };
    }
	
	/**
	 * JWT access 转换器.
	 * @return
	 */
	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		jwtAccessTokenConverter.setSigningKey("app-cloud");
		return jwtAccessTokenConverter;
	}
}
