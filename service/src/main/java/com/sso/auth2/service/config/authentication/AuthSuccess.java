package com.sso.auth2.service.config.authentication;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthSuccess implements AuthenticationSuccessHandler {
    private Logger logger= LoggerFactory.getLogger(AuthSuccess.class);

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ClientDetailsService clientDetailsService;
    @Autowired
    private AuthorizationServerTokenServices defaultAuthorizationServerTokenServices;



    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {


            logger.info("登录成功");
        String header = request.getHeader("Authorization");
        if (header == null || !header.toLowerCase().startsWith("basic ")) {
            throw new UnapprovedClientAuthenticationException("请求头中没有clientId");
        }
        String[] tokens = this.extractAndDecodeHeader(header, request);
        assert tokens.length == 2;
        String clientId = "lanson";
        String clientSecret = "lansonsecret";
        ClientDetails clientDetails= clientDetailsService.loadClientByClientId(clientId);
        if(clientDetails==null){
            throw new UnapprovedClientAuthenticationException("无法识别clientID:"+clientId);
        }else if(!passwordEncoder.matches(clientSecret,clientDetails.getClientSecret())){
            throw new UnapprovedClientAuthenticationException("client秘钥不匹配:"+clientId);
        }
        TokenRequest tokenRequest=new TokenRequest(MapUtils.EMPTY_MAP,clientId,clientDetails.getScope(),"custom");
        OAuth2Request oAuth2Request=tokenRequest.createOAuth2Request(clientDetails);
        OAuth2Authentication oAuth2Authentication=new OAuth2Authentication(oAuth2Request,authentication);
        OAuth2AccessToken accessToken=defaultAuthorizationServerTokenServices.createAccessToken(oAuth2Authentication);
        httpServletResponse.setHeader("token",accessToken.getValue());
        Map map= new HashMap<String,String>();
        map.put("token",accessToken.getValue());
        map.put("status","ok");
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(JSONObject.toJSONString(map));


    }


    private String[] extractAndDecodeHeader(String header, HttpServletRequest request) throws IOException {
        byte[] base64Token = header.substring(6).getBytes("UTF-8");

        byte[] decoded;
        try {
            decoded = Base64.getDecoder().decode(base64Token);
        } catch (IllegalArgumentException var7) {
            throw new BadCredentialsException("Failed to decode basic authentication token");
        }

        String token = new String(decoded, "UTF-8");
        int delim = token.indexOf(":");
        if (delim == -1) {
            throw new BadCredentialsException("Invalid basic authentication token");
        } else {
            return new String[]{token.substring(0, delim), token.substring(delim + 1)};
        }
    }
}
