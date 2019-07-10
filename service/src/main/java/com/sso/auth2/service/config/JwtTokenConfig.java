package com.sso.auth2.service.config;

import com.sso.auth2.service.prop.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@ConditionalOnProperty(prefix = "lanson.security" , name = "storeType" ,havingValue = "jwt",matchIfMissing = true)
public class JwtTokenConfig {
    @Autowired
    private SecurityProperties securityProperties;
    @Bean
    public JwtTokenStore jwtTokenStore(){
        return  new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter=new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(securityProperties.getOAuth2().getJwtSigningKey());
        return  jwtAccessTokenConverter;
    }

    @Bean
    @ConditionalOnMissingBean(name="jwtTokenEnhancer")
    public TokenEnhancer jwtTokenEnhancer(){
      return   new JwtTokenEnhance();
    }
}
