package com.sso.auth2.service.config;

import com.sso.auth2.service.config.authentication.AuthFailure;
import com.sso.auth2.service.config.authentication.AuthSuccess;
import com.sso.auth2.service.constants.MySecurityConstants;
import com.sso.auth2.service.filter.SimpleCORSFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;


@Configuration
@EnableWebSecurity
@Order(6)
public class OAuth2WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthSuccess authSuccess;
    @Autowired
    private AuthFailure authFailure;
    @Autowired
    private SimpleCORSFilter simpleCORSFilter;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(MySecurityConstants.DEFAULT_AUTH_LOGIN_PAGE)
                .loginProcessingUrl(MySecurityConstants.DEFAULT_AUTH_LOGIN_PROCESS_URL)
                .successHandler(authSuccess)
                .failureHandler(authFailure)
                .and()
                .authorizeRequests()
                .antMatchers(MySecurityConstants.DEFAULT_AUTH_LOGIN_PAGE,
                        MySecurityConstants.DEFAULT_VALIDATE_CODE_URL,
                        "/session/invalid",
                        "/loginPage")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                 .csrf().disable().sessionManagement().disable();
                 http.addFilterBefore(simpleCORSFilter, SecurityContextPersistenceFilter.class);
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .requestMatchers()   //　requestMatchers 配置　数组
//                .antMatchers("/oauth/**",MySecurityConstants.DEFAULT_AUTH_LOGIN_PAGE,"/home")
//                .and()
//                .authorizeRequests()         //authorizeRequests　配置权限　顺序为先配置需要放行的url 在配置需要权限的url，最后再配置.anyRequest().authenticated()
//                .antMatchers("/oauth/**").authenticated()
//                .and()
//                .formLogin()
//                .loginPage(MySecurityConstants.DEFAULT_AUTH_LOGIN_PAGE)
//                .permitAll();
//        http.addFilterBefore(simpleCORSFilter, SecurityContextPersistenceFilter.class);
//    }



}
