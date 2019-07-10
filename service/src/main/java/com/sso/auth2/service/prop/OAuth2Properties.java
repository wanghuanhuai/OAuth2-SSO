package com.sso.auth2.service.prop;

public class OAuth2Properties {
    private String jwtSigningKey = "lansonkey";
    private OAuth2ClientProperties[] client={};

    public OAuth2ClientProperties[] getClient() {
        return client;
    }

    public void setClient(OAuth2ClientProperties[] client) {
        this.client = client;
    }

    public String getJwtSigningKey() {
        return jwtSigningKey;
    }

    public void setJwtSigningKey(String jwtSigningKey) {
        this.jwtSigningKey = jwtSigningKey;
    }
}
