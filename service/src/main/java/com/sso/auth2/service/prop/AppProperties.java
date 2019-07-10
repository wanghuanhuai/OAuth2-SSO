package com.sso.auth2.service.prop;

public class AppProperties {

    private String signinProcessUrlOpenId ="/auth/openId";


    public String getSigninProcessUrlOpenId() {
        return signinProcessUrlOpenId;
    }

    public void setSigninProcessUrlOpenId(String signinProcessUrlOpenId) {
        this.signinProcessUrlOpenId = signinProcessUrlOpenId;
    }
}
