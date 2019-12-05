package entity.po;


/**
 * @author: 磊大大
 * @date: 2019/10/12 17:26
 */

public class AuthUser {
    private String userId;
    private String nickname;
    private String userPhone;
    private String userQq;
    private byte identity;
    private byte istrue;
    private byte complete;
    private String salt;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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

    public String getUserQq() {
        return userQq;
    }

    public void setUserQq(String userQq) {
        this.userQq = userQq;
    }

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

    public byte getComplete() {
        return complete;
    }

    public void setComplete(byte complete) {
        this.complete = complete;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

}
