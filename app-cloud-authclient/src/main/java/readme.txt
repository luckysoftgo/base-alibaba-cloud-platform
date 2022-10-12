一、oauth2的认证方式:
    https://docs.spring.io/spring-security/site/docs/5.0.4.RELEASE/reference/htmlsingle/#jc-oauth2login

二、认证方式种类:
    http://www.ruanyifeng.com/blog/2014/05/oauth_2_0.html

三、authclient 项目包含了 基于验证码的登录，内存的登录，短信的登录，微信登录等等。
核心类是：IntegrationUserDetailsService 实现了 spring-security 的 UserDetailsService 接口，重新实现了：loadUserByUsername 方法.




