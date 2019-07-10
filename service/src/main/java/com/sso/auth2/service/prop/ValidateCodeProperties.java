package com.sso.auth2.service.prop;

public class ValidateCodeProperties {

    private  ValidateImageProperties image=new ValidateImageProperties();
    private  ValidateSmsProperties sms=new ValidateSmsProperties();

    public ValidateImageProperties getImage() {
        return image;
    }

    public void setImage(ValidateImageProperties image) {
        this.image = image;
    }

    public ValidateSmsProperties getSms() {
        return sms;
    }

    public void setSms(ValidateSmsProperties sms) {
        this.sms = sms;
    }
}
