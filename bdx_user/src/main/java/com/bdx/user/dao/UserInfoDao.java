package com.bdx.user.dao;

import com.bdx.user.entity.po.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: 磊大大
 * @date: 2019/10/16 12:34
 */
public interface UserInfoDao extends JpaRepository<UserInfo,Integer>, JpaSpecificationExecutor<UserInfoDao> {

    UserInfo findUserInfoByUserId(String userId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE UserInfo u SET u.headImgUrl = ?1 WHERE u.userId = ?2")
    Integer updateUserHeadImgByUserId(String headImg, String userId);

    @Query(value = "SELECT head_img_url FROM user_info WHERE user_id = ?", nativeQuery = true)
    String findUserHeadImgByUserId(String userId);

    UserInfo findUserInfoByNickname(String nickname);

}
