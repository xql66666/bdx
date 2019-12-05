package com.bdx.manager.entity.vo;

/**
 * @author: 磊大大
 * @date: 2019/11/1 15:29
 *
 */
public class UserUpdateVO {

    private String nickname;
    private String userPhone;
    //private String userQq;
    private byte identity;
    private byte istrue;
    private String salt;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

//    public String getUserQq() {
//        return userQq;
//    }
//
//    public void setUserQq(String userQq) {
//        this.userQq = userQq;
//    }

    public byte getIdentity() {
        return identity;
    }

    public void setIdentity(byte identity) {
        this.identity = identity;
    }

    public byte getIstrue() {
        return istrue;
    }

    public void setIstrue(byte istrue) {
        this.istrue = istrue;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
