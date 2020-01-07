package com.bdx.manager.client;

import com.bdx.info.entity.param.InfoAddParam;
import entity.ResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: 磊大大
 * @date: 2019/12/31 16:02
 */
@FeignClient("bdx-info")
public interface InfoClient {

    @PostMapping(value = "/info/addInfo")
    ResponseEntity addInfo(@RequestBody InfoAddParam infoAddParam, @RequestParam("userId") String userId);
}
