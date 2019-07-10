package com.sso.auth2.service.constants;

public class MySecurityConstants {

    public static  String DEFAULT_PARAMETER_NAME_CODE_SMS ="smsCode";
    public static  String DEFAULT_PARAMETER_NAME_CODE_IMAGE ="imageCode";
    public static  String DEFAULT_AUTH_LOGIN_PAGE ="/auth/require";
    public static  String DEFAULT_AUTH_LOGIN_PROCESS_URL ="/api/login/account";
    public static  String DEFAULT_VALIDATE_CODE_URL ="/code/*";

    public static  String ACCESS_TOKEN ="access_token";
    public static  String REFRESH_TOKEN ="refresh_token";

    /**
     * 验证图片验证码时，http请求中默认的携带图片验证码信息的参数的名称
     */
    public static final String DEFAULT_REQUEST_PARAMETER_IMAGECODE = "imageCode";

    /**
     * 验证短信验证码时，http请求中默认的携带短信验证码信息的参数的名称
     */
    public static final String DEFAULT_REQUEST_PARAMETER_SMSCODE= "smsCode";
    /**
     * 社交登陆时， 传递的openId参数名
     */
    public static final String DEFAULT_REQUEST_PARAMETER_OPENID = "openId";

    /**
     * 社交登陆时，传递的providerId参数名
     */
    public static final String DEFAULT_REQUEST_PARAMETER_PROVIDERID = "providerId";
}
