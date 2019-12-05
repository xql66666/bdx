package com.bdx.user.util;

import com.bdx.user.entity.po.UserInfo;
import org.apache.commons.lang3.StringUtils;

/**
 * @author: 磊大大
 * @date: 2019/11/26 14:15
 */
public class FieldUtil {

    public static boolean userInfoIsNUll(UserInfo userInfo){
        if (StringUtils.isBlank(userInfo.getHeadImgUrl())) {
            return false;
        }
        if (StringUtils.isBlank(userInfo.getUserMail())) {
            return false;
        }
        if (userInfo.getBirthday() == null) {
            return false;
        }
        if (StringUtils.isBlank(userInfo.getHomecity())) {
            return false;
        }
        if (StringUtils.isBlank(userInfo.getUserQq())) {
            return false;
        }
        if (StringUtils.isBlank(userInfo.getSchool())) {
            return false;
        }
        if ("0".equals(String.valueOf(userInfo.getGrade())) || "null".equals(String.valueOf(userInfo.getGrade())) || userInfo.getGrade() <= 0) {
            return false;
        }
        if (StringUtils.isBlank(userInfo.getIntroduce())) {
            return false;
        }
        return true;
    }
}
