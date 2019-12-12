package com.bdx.sources.controller;


import com.bdx.sources.entity.param.SearchSourceParam;
import com.bdx.sources.entity.param.SourceParam;
import com.bdx.sources.entity.vo.SourceVO;
import com.bdx.sources.service.SourceService;
import com.bdx.sources.util.AttributeUtil;
import constants.ResultCodeBase;
import constants.TipConstBase;
import entity.PageRecordsDto;
import entity.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author: 磊大大
 * @date: 2019/11/29 14:34
 */
@RestController
@CrossOrigin
@RequestMapping("/source")
public class SourcesController {

    @Autowired
    private AttributeUtil attributeUtil;

    @Autowired
    private SourceService sourceService;

    @PostMapping(value = "/addSource")
    public ResponseEntity addSource(@RequestBody SourceParam sourceParama) {
        String userId = attributeUtil.getAttributeUserId();
        sourceService.addSource(userId, sourceParama);
        return new ResponseEntity(ResultCodeBase.CODE_SUCCESS, TipConstBase.OPERATION_SAVE_SUCCESS);
    }

    @PostMapping(value = "/searchSource/{currentPage}/{pageSize}")
    public ResponseEntity findSource(@PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize, @RequestBody SearchSourceParam searchSourceParam) {
        PageRecordsDto<SourceVO> data = sourceService.findSource(currentPage, pageSize, searchSourceParam);
        return new ResponseEntity<>(ResultCodeBase.CODE_SUCCESS, TipConstBase.OPERATION_GET_SUCCESS, data);
    }


}
