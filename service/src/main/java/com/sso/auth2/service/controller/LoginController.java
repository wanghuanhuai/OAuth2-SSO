package com.sso.auth2.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@CrossOrigin
public class LoginController {
    Logger log= LoggerFactory.getLogger(getClass());
    //封装了引发跳转请求的工具类  https://blog.csdn.net/honghailiang888/article/details/53671108
    private RequestCache requestCache = new HttpSessionRequestCache();
    // spring的工具类：封装了所有跳转行为策略类
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @GetMapping("/auth/require")
    public void loginPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("进入登录页面跳转!");
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (null != savedRequest) {
            String redirectUrl = savedRequest.getRedirectUrl();
            log.info("引发跳转的请求是：{}", redirectUrl);
                // 如果是HTML请求，那么就直接跳转到HTML，不再执行后面的代码
                redirectStrategy.sendRedirect(request, response, "/loginPage");
              return;
        }
        redirectStrategy.sendRedirect(request, response, "/loginPage");
        return;
    }




    @RequestMapping("/loginPage")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        redirectStrategy.sendRedirect(request, response, "http://localhost:8000/user/login");
       // return new ModelAndView("login");
    }

    @RequestMapping("/api/me")
    @ResponseBody
    public String me(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return "test";
    }




}
