package com.sso.auth2.service.prop;

public class ValidateSmsProperties {
    /**
     * 验证码位数
     */
  private  int  codeLength = 6 ;
    /**
     * 在多少秒后过期
     */
  private  int  expireIn = 180 ;
    /**
     *短信验证码拦截的请求地址
     */
  private  String url ="";

    public int getCodeLength() {
        return codeLength;
    }

    public void setCodeLength(int codeLength) {
        this.codeLength = codeLength;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
