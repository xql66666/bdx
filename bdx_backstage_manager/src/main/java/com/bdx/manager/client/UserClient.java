package com.bdx.manager.client;


import entity.ResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: 磊大大
 * @date: 2019/10/17 14:45
 */
@FeignClient("bdx-user")
public interface UserClient {

    @RequestMapping(value = "/user/userInfo/{userId}", method = RequestMethod.POST)
    ResponseEntity findUserInfoById(@PathVariable("userId") String userId);


}
