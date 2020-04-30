package com.bdx.user.client;


import entity.ResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: 磊大大
 * @date: 2019/12/16 13:41
 */
@FeignClient("bdx-sources")

public interface SourceClient {

    @PostMapping(value = "/source/searchMySource")
    ResponseEntity findMySource();


}
