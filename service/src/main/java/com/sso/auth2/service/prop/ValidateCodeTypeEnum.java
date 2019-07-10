package com.sso.auth2.service.prop;


import com.sso.auth2.service.constants.MySecurityConstants;

public enum ValidateCodeTypeEnum {
    IMAGE {
        @Override
        public String getParameterNameOnValidate() {
            return MySecurityConstants.DEFAULT_REQUEST_PARAMETER_IMAGECODE;
        }
    },
    SMS {
        @Override
        public String getParameterNameOnValidate() {
            return MySecurityConstants.DEFAULT_REQUEST_PARAMETER_SMSCODE;
        }
    };

    public abstract String getParameterNameOnValidate();
}
