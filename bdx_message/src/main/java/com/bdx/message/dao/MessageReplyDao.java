package com.bdx.message.dao;

import com.bdx.message.entity.po.MessageReply;
import com.bdx.message.entity.vo.ReplyVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author: 磊大大
 * @date: 2019/12/3 16:01
 */
public interface MessageReplyDao extends JpaRepository<MessageReply, String>, JpaSpecificationExecutor<MessageReply> {

    @Query(value = "SELECT new com.bdx.message.entity.vo.ReplyVO(m.messageReplyText, u.nickname, m.createTime) " +
            "FROM MessageReply m LEFT JOIN User u ON u.userId = m.userId where m.messageId = ?1 " +
            "ORDER BY m.createTime DESC", nativeQuery = false)
    List<ReplyVO> findMessageReplyListByMessageId(String messageId);
}
