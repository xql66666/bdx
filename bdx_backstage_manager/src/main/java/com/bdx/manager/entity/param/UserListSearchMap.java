package com.bdx.manager.entity.param;

/**
 * @author: 磊大大
 * @date: 2019/10/31 10:18
 */
public class UserListSearchMap {

    private String nickname;
    private String userPhone;
    private String starttime;
    private String endtime;

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

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
}
