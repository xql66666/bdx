package com.bdx.message.service;

import com.bdx.message.dao.MessageDao;
import com.bdx.message.dao.MessageReplyDao;
import com.bdx.message.entity.param.MessageParam;
import com.bdx.message.entity.po.Message;
import com.bdx.message.entity.vo.MessageVO;
import com.bdx.message.entity.vo.ReplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.Date;
import java.util.List;

/**
 * @author: 磊大大
 * @date: 2019/11/29 17:14
 */
@Service
public class MessageService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private MessageReplyDao messageReplyDao;


    /**
     * 添加留言
     * @param userId
     * @param messageParam
     */
    public void addMessage(String userId, MessageParam messageParam) {
        Message message = new Message();
        message.setMessageId("ly" + idWorker.nextId());
        message.setUserId(userId);
        message.setMessageTitle(messageParam.getMsgTitle());
        message.setMessageContext(messageParam.getMsgContext());
        message.setMessageNum(0);
        message.setMessageGood(0);
        message.setMessageLevel((byte) 4);
        message.setCreateTime(new Date());
        messageDao.save(message);
    }

    /**
     * 按页查找留言
     * @param currentPage
     * @return
     */
    public List<MessageVO> findMessageList(int currentPage) {
        System.out.println("curr的值" + currentPage);
        List<MessageVO> messageList = messageDao.findMessageList(new PageRequest(currentPage-1, 5));


        return messageList;
    }

    /**
     * 根据留言id查找这个留言的回复
     * @param messageId
     * @return
     */
    public List<ReplyVO> findReplyByMessageId(String messageId) {
        System.out.println("mess的值" + messageId);
        List<ReplyVO> replyVOList = messageReplyDao.findMessageReplyListByMessageId(messageId);
        return replyVOList;
    }
}
