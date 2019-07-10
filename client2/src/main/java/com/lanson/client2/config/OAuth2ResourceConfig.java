package com.lanson.client2.config;


import com.lanson.client2.config.handle.TokenClearLogoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

@Configuration
@EnableResourceServer   //注解来开启资源服务器
public class OAuth2ResourceConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    private TokenClearLogoutHandler tokenClearLogoutHandler;



    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 配置那些资源需要保护的
        http
                .authorizeRequests()
                .antMatchers("/api/logout")
                .permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .logout()
                .logoutUrl("/api/logout")//默认就是"/logout"
                .addLogoutHandler(tokenClearLogoutHandler)
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler()) //logout成功后返回200
                .logoutSuccessUrl("http://localhost:8080/logout");
                //.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler())  //权限认证失败业务处理
               // .authenticationEntryPoint(customAuthenticationEntryPoint());  //认证失败的业务处理
    }
}
