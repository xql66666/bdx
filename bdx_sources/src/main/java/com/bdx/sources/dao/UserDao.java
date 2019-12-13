package com.bdx.sources.dao;


import com.bdx.sources.entity.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

/**
 * @author: 磊大大
 * @date: 2019/10/14 17:19
 */
@Component
public interface UserDao extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {

    @Query(value = "SELECT u.nickname FROM user u WHERE user_id = ?", nativeQuery = true)
    String findNickNameByUserId(String userId);




}
