package com.bdx.user.entity.param;

/**
 * @author: 磊大大
 * @date: 2019/11/15 9:53
 */
public class RegisterUserParam {

    private String userPhone;
    private String password;
    private String messageCode;

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }
}
