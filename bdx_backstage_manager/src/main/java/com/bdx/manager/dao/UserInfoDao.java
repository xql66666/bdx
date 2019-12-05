package com.bdx.manager.dao;

import com.bdx.manager.entity.po.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author: 磊大大
 * @date: 2019/10/22 15:26
 */
public interface UserInfoDao extends JpaRepository<UserInfo,Integer>, JpaSpecificationExecutor<UserInfo> {

    UserInfo findUserInfoByUserId(String userId);
}
