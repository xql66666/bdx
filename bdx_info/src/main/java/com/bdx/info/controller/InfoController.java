package com.bdx.info.controller;

import com.bdx.info.entity.param.InfoAddParam;
import com.bdx.info.service.InfoService;
import com.bdx.info.util.AttributeUtil;
import constants.ResultCodeBase;
import constants.TipConstBase;
import entity.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: 磊大大
 * @date: 2019/12/31 14:14
 */
@RestController
@CrossOrigin
@RequestMapping("/info")
public class InfoController {

    @Autowired
    private InfoService infoService;

    @Autowired
    private AttributeUtil attributeUtil;

//    @PostMapping(value = "/addInfo")
//    public ResponseEntity addInfo(@RequestBody InfoAddParam infoAddParam) {
//        infoService.addInfo(infoAddParam, attributeUtil.getAttributeUserId());
//        return new ResponseEntity(ResultCodeBase.CODE_SUCCESS, TipConstBase.OPERATION_SAVE_SUCCESS);
//    }

    /**
     * 添加资讯
     * @param infoAddParam
     * @return
     */
    @PostMapping(value = "/addInfo")
    public ResponseEntity addInfo(@RequestBody InfoAddParam infoAddParam, String userId) {
        infoService.addInfo(infoAddParam, userId);
        return new ResponseEntity(ResultCodeBase.CODE_SUCCESS, TipConstBase.OPERATION_SAVE_SUCCESS);
    }
}
