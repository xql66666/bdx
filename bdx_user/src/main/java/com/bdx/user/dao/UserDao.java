package com.bdx.user.dao;

import com.bdx.user.entity.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: 磊大大
 * @date: 2019/10/14 17:19
 */
public interface UserDao extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {

    @Query(value = "SELECT i.identity_name FROM identity i WHERE i.identity_id = (SELECT u.identity FROM user u WHERE u.user_id = ?);", nativeQuery = true)
    String findRoleByUserId(String userId);

    User findUserByUserPhone(String phone);

    @Query(value = "SELECT user_phone FROM user WHERE user_id = ?", nativeQuery = true)
    String findUserPhoneByUserId(String userId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE User u SET u.nickname = ?1 WHERE u.userId = ?2")
    Integer updateUserNicknameByUserId(String nickname, String userId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE User u SET u.password = ?1 WHERE u.userId = ?2")
    Integer updateUserPasswordByUserId(String pass, String userId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE User u SET u.complete = ?1 WHERE u.userId = ?2")
    Integer updateUserCompleteByUserId(byte complete, String userId);

    @Query(value = "SELECT complete FROM user WHERE user_id = ?", nativeQuery = true)
    Byte findUserCompleteByUserId(String userId);


}
