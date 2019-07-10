package com.sso.auth2.service.prop;


import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "lanson.security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();
    private ValidateCodeProperties code = new ValidateCodeProperties();
    private OAuth2Properties OAuth2=new OAuth2Properties();

    private MySocialProperties social=new MySocialProperties();

    AppProperties app=new AppProperties();
    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }

    public OAuth2Properties getOAuth2() {
        return OAuth2;
    }

    public void setOAuth2(OAuth2Properties OAuth2) {
        this.OAuth2 = OAuth2;
    }

    public MySocialProperties getSocial() {
        return social;
    }

    public void setSocial(MySocialProperties social) {
        this.social = social;
    }

    public AppProperties getApp() {
        return app;
    }

    public void setApp(AppProperties app) {
        this.app = app;
    }
}
