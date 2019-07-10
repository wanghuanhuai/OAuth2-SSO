package com.sso.auth2.service.config;


import com.sso.auth2.service.config.authentication.AuthFailure;
import com.sso.auth2.service.config.authentication.AuthSuccess;
import com.sso.auth2.service.constants.MySecurityConstants;
import com.sso.auth2.service.prop.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

@Configuration
@EnableResourceServer
public class Auth2ResourceConfig extends ResourceServerConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Autowired
    private AuthSuccess authSuccess;
    @Autowired
    private AuthFailure authFailure;
    private SecurityProperties securityProperties;
    @Autowired
    private OAuth2WebSecurityExpressionHandler expressionHandler;
    @Bean
    public OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler(ApplicationContext applicationContext) {
        OAuth2WebSecurityExpressionHandler expressionHandler = new OAuth2WebSecurityExpressionHandler();
        expressionHandler.setApplicationContext(applicationContext);
        return expressionHandler;
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {


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
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
        //authMatchConfigManager.config(http.authorizeRequests());
    }

    /**
     * access控制表达式
     * @param resources
     * @throws Exception
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.expressionHandler(expressionHandler);
    }

}
