package com.bdx.info.dao;

import com.bdx.info.entity.po.InfoReply;
import com.bdx.info.entity.vo.InfoReplyVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author: xuqianlei
 * @date: 2020/1/19 15:08
 * @version:
 */
public interface InfoReplyDao extends JpaRepository<InfoReply, Integer>, JpaSpecificationExecutor<InfoReply> {
    @Query(value = "SELECT new com.bdx.info.entity.vo.InfoReplyVO(u.nickname, u.headImgUrl, i.infoReplyContent, i.createTime) " +
            "FROM InfoReply i left join UserInfo u ON u.userId = i.infoReplyUser where i.infoId = :infoId " +
            "order by i.createTime desc", nativeQuery = false)
    List<InfoReplyVO> findInfoReplyByInfoId(@Param("infoId") Integer infoId);
}
