package com.bdx.manager.controller;

import com.bdx.info.entity.param.InfoAddParam;
import com.bdx.manager.client.InfoClient;
import com.bdx.manager.util.AttributeUtil;
//import com.bdx.user.util.QiNiuUtil;
import com.bdx.manager.util.QiNiuUtil;
import constants.ResultCodeBase;
import constants.TipConstBase;
import entity.ResponseEntity;
import exception.core.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


/**
 * @author: 磊大大
 * @date: 2019/12/31 16:08
 */
@RestController
@CrossOrigin
@RequestMapping("/backstage/info")
public class InfoController {

    @Autowired
    private InfoClient infoClient;

    @Autowired
    private AttributeUtil attributeUtil;

    /**
     * 添加资讯
     * @param infoAddParam
     * @return
     */
    @PostMapping(value = "/addInfo")
    public ResponseEntity addInfo(@RequestBody InfoAddParam infoAddParam) {
        String userId = attributeUtil.getAttributeUserId();
        ResponseEntity responseEntity = infoClient.addInfo(infoAddParam, userId);
        return  responseEntity;
    }

    /**
     * 展示资讯
     * @param currentPage
     * @return
     */
    @PostMapping(value = "/findAllInfo/{currentPage}")
    public ResponseEntity findAllInfo(@PathVariable("currentPage") String currentPage) {
        System.out.println("当前页是：" + currentPage);
        return infoClient.findAllInfo(currentPage);
    }

    /**
     * 发布资讯是内容添加的图片
     * @param multipartHttpServletRequest
     * @return
     */
    @PostMapping(value = "/uploadInfoImg")
    public Map uploadInfoImg(MultipartHttpServletRequest multipartHttpServletRequest) {

        HashMap map = new HashMap();
        MultipartFile multipartFile = multipartHttpServletRequest.getFile(multipartHttpServletRequest.getFileNames().next());
        try {
            InputStream inputStream = multipartFile.getInputStream();
            String infoImg = QiNiuUtil.qiniuUploadHeadImg(inputStream);
            if (infoImg == null || "".equals(infoImg)){
                throw new BusinessException("上传资讯图片失败");
            }
            map.put("errno", 0);
            map.put("data", infoImg);

        } catch (IOException e) {
            e.printStackTrace();
        }
        //return new ResponseEntity<>(ResultCodeBase.CODE_SUCCESS, TipConstBase.OPERATION_UPDATE_SUCCESS, map);
        return map;
    }

}
