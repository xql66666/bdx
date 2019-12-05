package com.bdx.zuul.filter.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.AesUtil;


/**
 * @author: 磊大大
 * @date: 2019/11/27 14:32
 */
@Component
public class SignUtils {

    @Autowired
    private AesUtil aesUtil;

    /**
     * 时间戳请求最小限制
     */
    private static final long MAX_REQUEST = 10 * 1000L;


    public boolean timeIsTrue(String timestamp) {
        long now = System.currentTimeMillis();
        long time = Long.parseLong(aesUtil.aesDecrypt(timestamp));
        System.out.println("时间为：");
        System.out.println(now - time);
        if (now - time > MAX_REQUEST && now > time) {
            return false;
        }else {
            return true;
        }
    }

    public boolean signIsTrue(String timestamp) {
        return false;
    }


}
