package com.sso.auth2.service.prop;

public class ValidateImageProperties extends ValidateSmsProperties{
    public ValidateImageProperties() {
      setCodeLength(4);
      setExpireIn(120);
      setUrl("");
    }
    /**
     * 页面宽度
     */
  private   int width = 85;
    /**
     * 页面高度
     */
  private  int  height = 20;



    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
