package com.bdx.manager.util;

import exception.core.TokenErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: 磊大大
 * @date: 2019/11/23 21:36
 */
@Component
public class AttributeUtil {

    @Autowired
    private HttpServletRequest request;

    public String getAttributeUserId() {
        String userId = (String) request.getAttribute("userId");
        if (userId == null || "".equals(userId)) {
            throw new TokenErrorException();
        }
        return userId;
    }
}
