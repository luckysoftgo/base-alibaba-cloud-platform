package com.application.cloud.auth.integration;

import com.application.cloud.auth.integration.api.IntegrationAuthenticator;
import com.application.cloud.auth.integration.consts.AuthClientConst;
import com.application.cloud.auth.integration.dto.IntegrationAuthentication;
import com.application.cloud.auth.integration.type.ClientAuthType;
import com.application.cloud.common.core.utils.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * 集成认证的过滤配置.
 * @author : 孤狼
 * @NAME: IntegrationAuthenticationFilter
 * @DESC: IntegrationAuthenticationFilter 类设计
 **/
@Component
public class IntegrationAuthenticationFilter extends GenericFilterBean implements ApplicationContextAware {
	
	/**
	 * 认证类型 : ClientAuthType 中的值.
	 */
    private static final String AUTH_TYPE_PARM_NAME = AuthClientConst.AUTH_TYPE_PARM_NAME;
	/**
	 * 认证地址.
	 */
	private static final String OAUTH_TOKEN_URL = AuthClientConst.OAUTH_TOKEN_URL;
	/**
	 *
	 * 认证器--(短信，验证码，普通验证方式)
	 *
	 */
	private Collection<IntegrationAuthenticator> authenticators;
	/**
	 * 全局的 context 对象
	 */
    private ApplicationContext applicationContext;
	/**
	 * 请求匹配.
	 */
	private RequestMatcher requestMatcher;

    public IntegrationAuthenticationFilter(){
        this.requestMatcher = new OrRequestMatcher(
                new AntPathRequestMatcher(OAUTH_TOKEN_URL, "GET"),
                new AntPathRequestMatcher(OAUTH_TOKEN_URL, "POST")
        );
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if(requestMatcher.matches(request)){
            //设置集成登录信息.
            IntegrationAuthentication integrationAuthentication = new IntegrationAuthentication();
	        //输入的参数.
            Map<String, String[]> params = request.getParameterMap();
	        //授权方式,默认密码授权方式.
	        String authType = Objects.toString(request.getParameter(AUTH_TYPE_PARM_NAME),"");
	        if (StringUtils.isEmpty(authType)){
	        	authType = Objects.toString(params.get(AUTH_TYPE_PARM_NAME), ClientAuthType.INSTANCE_AUTH_TYPE.getAuthType());
	        }
	        integrationAuthentication.setAuthType(authType);
	        integrationAuthentication.setAuthParam(Objects.toString(request.getParameter(AuthClientConst.AUTH_PASSWORD),""));
            integrationAuthentication.setAuthParameters(params);
            IntegrationAuthenticationContext.set(integrationAuthentication);
            try{
                //预处理
                this.prepare(integrationAuthentication);
                filterChain.doFilter(request,response);
                //后置处理
                this.complete(integrationAuthentication);
            }finally {
                IntegrationAuthenticationContext.clear();
            }
        }else{
            filterChain.doFilter(request,response);
        }
    }

    /**
     * 进行预处理
     * @param integrationAuthentication
     */
    private void prepare(IntegrationAuthentication integrationAuthentication) {
        //延迟加载认证器
        if(this.authenticators == null){
            synchronized (this){
                Map<String,IntegrationAuthenticator> integrationAuthenticatorMap = applicationContext.getBeansOfType(IntegrationAuthenticator.class);
                if(integrationAuthenticatorMap != null){
                    this.authenticators = integrationAuthenticatorMap.values();
                }
            }
        }
        if(this.authenticators == null){
            this.authenticators = new ArrayList<>();
        }
        for (IntegrationAuthenticator authenticator: authenticators) {
            if(authenticator.support(integrationAuthentication)){
                authenticator.prepare(integrationAuthentication);
            }
        }
    }

    /**
     * 后置处理
     * @param integrationAuthentication
     */
    private void complete(IntegrationAuthentication integrationAuthentication){
        for (IntegrationAuthenticator authenticator: authenticators) {
            if(authenticator.support(integrationAuthentication)){
                authenticator.complete(integrationAuthentication);
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
