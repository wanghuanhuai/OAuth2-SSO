package com.sso.auth2.service.config.authentication;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.sso.auth2.service.prop.LoginType;
import com.sso.auth2.service.prop.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthFailure implements AuthenticationFailureHandler {
    private Logger logger= LoggerFactory.getLogger(AuthFailure.class);

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SecurityProperties securityProperties;
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        logger.info("登录失败");
        if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
          //  httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            Map map= new HashMap<String,String>();
            map.put("status","error");
            map.put("message",e.getMessage());
            map.put("type","account");
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.getWriter().write(JSONObject.toJSONString(map));
            //httpServletResponse.setContentType("application/json;charset=UTF-8");
           // httpServletResponse.getWriter().write(objectMapper.writeValueAsString(e.getMessage()));
        }else{
            //表单验证逻辑
        }

    }
}
