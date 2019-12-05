package com.bdx.manager.dao;

import com.bdx.manager.entity.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author: 磊大大
 * @date: 2019/10/22 11:14
 */
public interface UserDao extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {
    List<User> findAllByUserIdNotNull();

    User findUserByUserId(String userId);

    User findUserByUserPhone(String phone);

    @Query(value = "SELECT i.identity_name FROM identity i WHERE i.identity_id = (SELECT u.identity FROM user u WHERE u.user_id = ?);", nativeQuery = true)
    String findRoleByUserId(String userId);

    @Query(value = "SELECT user_phone FROM user WHERE user_id = ?", nativeQuery = true)
    String findUserPhoneByUserId(String userId);


}
