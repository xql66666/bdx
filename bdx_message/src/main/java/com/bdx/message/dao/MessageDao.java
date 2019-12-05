package com.bdx.message.dao;

import com.bdx.message.entity.po.Message;
import com.bdx.message.entity.vo.MessageVO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author: 磊大大
 * @date: 2019/11/29 15:10
 */
public interface MessageDao extends JpaRepository<Message,String>, JpaSpecificationExecutor<Message> {

    @Query(value = "SELECT new com.bdx.message.entity.vo.MessageVO(u.nickname, m.messageId, m.messageTitle, m.messageContext, m.messageGood, m.messageNum, m.createTime, m.messageLevel) " +
            "FROM Message m LEFT JOIN User u ON u.userId = m.userId WHERE m.messageLevel > 0 " +
            "ORDER BY m.messageLevel, m.createTime DESC", nativeQuery = false)
    List<MessageVO> findMessageList(Pageable pageable);

}

