package com.bdx.message.controller;

import com.bdx.message.entity.param.MessageParam;
import com.bdx.message.entity.vo.MessageVO;
import com.bdx.message.entity.vo.ReplyVO;
import com.bdx.message.service.MessageService;
import com.bdx.message.util.AttributeUtil;
import constants.ResultCodeBase;
import constants.TipConstBase;
import entity.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: 磊大大
 * @date: 2019/11/29 14:34
 */
@RestController
@CrossOrigin
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private AttributeUtil attributeUtil;

    @Autowired
    private MessageService messageService;

    /**
     * 添加留言
     * @return
     */
    @RequestMapping(value = "/addmessage", method = RequestMethod.POST)
    public ResponseEntity addMessage(@RequestBody MessageParam messageParam) {
        messageService.addMessage(attributeUtil.getAttributeUserId(), messageParam);
        System.out.println("标题和内容================");
        System.out.println(messageParam.getMsgTitle());
        System.out.println(messageParam.getMsgContext());
        return new ResponseEntity(ResultCodeBase.CODE_SUCCESS, TipConstBase.OPERATION_SAVE_SUCCESS);
    }

    /**
     * 查看留言 返回留言id及回复数
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/findallmessage/{currentPage}", method = RequestMethod.POST)
    public ResponseEntity findMessageList(@PathVariable("currentPage") String currentPage) {
        List<MessageVO> messageVOList =  messageService.findMessageList(Integer.parseInt(currentPage));
        return new ResponseEntity<>(ResultCodeBase.CODE_SUCCESS, TipConstBase.OPERATION_GET_SUCCESS, messageVOList);
    }

    /**
     * 查看回复
     * @param messageId
     * @return
     */
    @RequestMapping(value = "/findReplyById/{messageId}", method = RequestMethod.POST)
    public ResponseEntity findReplyList(@PathVariable("messageId") String messageId) {
        List<ReplyVO> replyVOList =  messageService.findReplyByMessageId(messageId);
        return new ResponseEntity(ResultCodeBase.CODE_SUCCESS, TipConstBase.OPERATION_GET_SUCCESS);
    }
}
