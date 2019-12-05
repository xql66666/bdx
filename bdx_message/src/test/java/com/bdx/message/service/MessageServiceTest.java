package com.bdx.message.service;

import com.bdx.message.entity.vo.ReplyVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author: 磊大大
 * @date: 2019/12/3 16:12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MessageServiceTest {

    @Autowired
    private MessageService messageService;

    @Test
    public void addMessage() {
    }

    @Test
    public void findMessageList() {
    }

    @Test
    public void findReplyByMessageId() {

        List<ReplyVO> list = messageService.findReplyByMessageId("ly1201392048004009984");
        for (ReplyVO replyVO : list) {
            System.out.println("回复为" + replyVO.toString());
        }
    }
}
