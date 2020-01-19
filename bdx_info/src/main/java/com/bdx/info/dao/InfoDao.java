package com.bdx.info.dao;

import com.bdx.info.entity.po.Info;
import com.bdx.info.entity.vo.InfoVO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: 磊大大
 * @date: 2019/12/31 14:24
 */
public interface InfoDao extends JpaRepository<Info, Integer>, JpaSpecificationExecutor<Info> {

    @Query(value = "SELECT new com.bdx.info.entity.vo.InfoVO(i.id, u.nickname, u.headImgUrl ,i.infoName, i.infoContent, i.infoReplyNum, i.infoGood, i.infoType, i.createTime) " +
            "FROM Info i left join UserInfo u ON u.userId = i.infoUser where i.infoType > 0 " +
            "order by i.createTime desc", nativeQuery = false)
    List<InfoVO> findInfoList(Pageable pageable);

    @Query(value = "SELECT new com.bdx.info.entity.vo.InfoVO(i.id, u.nickname, u.headImgUrl ,i.infoName, i.infoContent, i.infoReplyNum, i.infoGood, i.infoType, i.createTime) " +
            "FROM Info i left join UserInfo u ON u.userId = i.infoUser where i.id = :infoId and i.infoType > 0" +
            "order by i.createTime desc", nativeQuery = false)
    InfoVO findInfoDetail(@Param("infoId") Integer infoId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Info i SET i.infoReplyNum = i.infoReplyNum + 1 WHERE i.id = :infoId")
    Integer updateInfoCommentNumByInfoId(@Param("infoId")int infoId);
}
