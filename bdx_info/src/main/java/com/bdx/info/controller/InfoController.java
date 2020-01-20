package com.bdx.info.controller;

import com.bdx.info.entity.param.InfoAddParam;
import com.bdx.info.entity.param.InfoCommentAddParam;
import com.bdx.info.entity.vo.InfoVO;
import com.bdx.info.service.InfoService;
import com.bdx.info.util.AttributeUtil;
import constants.ResultCodeBase;
import constants.TipConstBase;
import entity.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 获取当前页的资讯
     * @param currentPage
     * @return
     */
    @PostMapping(value = "/findAllInfo/{currentPage}")
    public ResponseEntity findAllInfo(@PathVariable("currentPage") String currentPage) {
        List<InfoVO> infoVOLists =  infoService.findInfoList(Integer.parseInt(currentPage));
        return new ResponseEntity<>(ResultCodeBase.CODE_SUCCESS, TipConstBase.OPERATION_GET_SUCCESS, infoVOLists);
    }

    /**
     * 根据类型查询不同列表
     * @param currentPage
     * @param infoType
     * @return
     */
    @PostMapping(value = "/findAllInfoByType/{currentPage}/{infoType}")
    public ResponseEntity findAllInfoByType(@PathVariable("currentPage") String currentPage, @PathVariable("infoType") int infoType) {
        List<InfoVO> infoVOLists = infoService.findInfoListByType(Integer.parseInt(currentPage), infoType);
        return new ResponseEntity<>(ResultCodeBase.CODE_SUCCESS, TipConstBase.OPERATION_GET_SUCCESS, infoVOLists);
    }

    /**
     * 根据id查询资讯详情页
     * @param infoId
     * @return
     */
    @PostMapping(value = "/findInfoById/{infoId}")
    public ResponseEntity findInfoDetailById(@PathVariable("infoId") String infoId) {
        return new ResponseEntity<>(ResultCodeBase.CODE_SUCCESS, TipConstBase.OPERATION_GET_SUCCESS, infoService.findInfoDetail(infoId));
    }

    /**
     * 给评论添加回复
     * @param infoCommentAddParam
     * @return
     */
    @PostMapping(value = "/addInfoComment")
    public ResponseEntity addInfoComment(@RequestBody InfoCommentAddParam infoCommentAddParam) {
        infoService.addInfoComment(infoCommentAddParam, attributeUtil.getAttributeUserId());
        return new ResponseEntity(ResultCodeBase.CODE_SUCCESS, TipConstBase.OPERATION_SAVE_SUCCESS);
    }

    /**
     * 查询资讯的回复
     * @param infoId
     * @return
     */
    @PostMapping(value = "/findInfoComment/{infoId}")
    public ResponseEntity findInfoCommentList(@PathVariable("infoId") String infoId) {
        return new ResponseEntity<>(ResultCodeBase.CODE_SUCCESS, TipConstBase.OPERATION_GET_SUCCESS, infoService.findInfoReplyListById(infoId));
    }
}
