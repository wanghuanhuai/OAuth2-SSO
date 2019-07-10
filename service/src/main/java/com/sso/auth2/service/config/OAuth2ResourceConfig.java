package com.sso.auth2.service.config;


import com.sso.auth2.service.filter.PermitAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

@Configuration
@EnableResourceServer   //注解来开启资源服务器
public class OAuth2ResourceConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private DefaultTokenServices tokenServices;
    @Autowired
    private TokenStore jwtTokenStore;
    @Autowired
    private PermitAuthenticationFilter permitAuthenticationFilter;


    @Override
    public void configure(HttpSecurity http) throws Exception {

        // 配置那些资源需要保护的
        http.requestMatchers().antMatchers("/api/**")
                .and()
                .authorizeRequests()
                .antMatchers("/api/**").authenticated();

                //.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler())  //权限认证失败业务处理
               // .authenticationEntryPoint(customAuthenticationEntryPoint());  //认证失败的业务处理
        http.addFilterBefore(permitAuthenticationFilter, AbstractPreAuthenticatedProcessingFilter.class); //自定义token过滤 token校验失败后自定义返回数据格式

    }
}
