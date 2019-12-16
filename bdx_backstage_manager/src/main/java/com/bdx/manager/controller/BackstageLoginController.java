package com.bdx.manager.controller;

import com.bdx.manager.client.UserClient;

import com.bdx.manager.entity.param.LoginUserParam;
import com.bdx.manager.entity.param.UserListSearchMap;
import com.bdx.manager.entity.param.UserParam;

import com.bdx.manager.entity.po.User;
import com.bdx.manager.entity.vo.UserVO;
import com.bdx.manager.service.BackstageLoginUserService;
import constants.IdentityEnum;
import constants.RedisKey;
import constants.ResultCodeBase;
import constants.TipConstBase;
import entity.PageRecordsDto;
import entity.ResponseEntity;
import exception.core.TokenErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import util.AesUtil;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author: 磊大大
 * @date: 2019/10/15 14:49
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/backstage")
public class BackstageLoginController {

    @Autowired
    private UserClient userClient;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private BackstageLoginUserService backstageLoginUserService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private AesUtil aesUtil;


    /**
     * 修改用户的User信息
     * 传ID 但是Id不修改 创建时间不修改 更新时间自动修改 不需要传入
     * @param param
     * @return
     */
    @RequestMapping(value = "/updateUser/{userId}", method = RequestMethod.POST)
    public ResponseEntity updateUser(@PathVariable("userId") String userId, @RequestBody UserParam param) {
        backstageLoginUserService.updateUser(userId, param);
        return new ResponseEntity<>(ResultCodeBase.CODE_SUCCESS, TipConstBase.OPERATION_UPDATE_SUCCESS);
    }

    /**
     * 查看所有用户的User信息(社团用户)
     * @return
     */
    @RequestMapping(value = "/allUser/{currentPage}/{pageSize}", method = RequestMethod.POST)
    public ResponseEntity allUser(@PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize, @RequestBody UserListSearchMap userListSearchMap) {
        PageRecordsDto<UserVO> data = backstageLoginUserService.findAllUser(currentPage, pageSize, userListSearchMap);
        return new ResponseEntity<PageRecordsDto>(ResultCodeBase.CODE_SUCCESS, TipConstBase.OPERATION_GET_SUCCESS, data);
    }

    /**
     * 查看所有用户的User信息（其他用户）
     * @return
     */
    @RequestMapping(value = "/allOtherUser/{currentPage}/{pageSize}", method = RequestMethod.POST)
    public ResponseEntity allOtherUser(@PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize, @RequestBody UserListSearchMap userListSearchMap) {
        PageRecordsDto<UserVO> data = backstageLoginUserService.findAllOtherUser(currentPage, pageSize, userListSearchMap);
        return new ResponseEntity<PageRecordsDto>(ResultCodeBase.CODE_SUCCESS, TipConstBase.OPERATION_GET_SUCCESS, data);
    }

    /**
     * 后台登录
     * @param user
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody LoginUserParam user) {

        User userLogin = backstageLoginUserService.login(aesUtil.aesDecrypt(user.getUsername()), aesUtil.aesDecrypt(user.getPassword()));
        if (userLogin == null) {
            return new ResponseEntity(ResultCodeBase.CODE_BAD_REQUEST, TipConstBase.OPERATION_LOGIN_ERROR);
        }
        //JWT
        String role = backstageLoginUserService.findRoleByUserId(userLogin.getUserId());
        if (!role.equals(IdentityEnum.MANAGE.getDesc()) && !role.equals(IdentityEnum.ROOT.getDesc())) {
            //权限不足
            return new ResponseEntity<>(ResultCodeBase.CODE_ERROR_USER_IDENTITY_ERROR, TipConstBase.OPERATION_IDENTITY_ERROR);
        }
        String token = jwtUtil.createJWT(userLogin.getUserId(), userLogin.getUserPhone(), role);
        redisTemplate.opsForValue().set(RedisKey.REDIS_TOKEN + userLogin.getUserId(), token, 6, TimeUnit.HOURS);
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", token);
        return new ResponseEntity<>(ResultCodeBase.CODE_SUCCESS, TipConstBase.OPERATION_LOGIN_SUCCESS, map);
    }


    /**
     * 根据用户的UserId查找UserInfo
     * @param userId
     * @return
     */
    @RequestMapping(value = "/userInfo/{userId}", method = RequestMethod.POST)
    public ResponseEntity findUserInfoById(@PathVariable("userId") String userId) {
        ResponseEntity response = userClient.findUserInfoById(userId);
        return response;
    }

    /**
     * 根据用户的UserId查找User
     * @param userId
     * @return
     */
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.POST)
    public ResponseEntity findUserById(@PathVariable("userId") String userId) {
        return new ResponseEntity<>(ResultCodeBase.CODE_SUCCESS, TipConstBase.OPERATION_GET_SUCCESS, backstageLoginUserService.findUserById(userId));
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity logout() {
        Boolean flag = backstageLoginUserService.logout();
        if (!flag) {
            throw new TokenErrorException("退出失败！");
        }
        return new ResponseEntity(ResultCodeBase.CODE_SUCCESS, TipConstBase.OPERATION_LOGOUT_SUCCESS);
    }

    /**
     * 登录后调用此接口获得登录用户信息
     * @return
     */
    @RequestMapping(value = "/getInfo", method = RequestMethod.POST)
    public ResponseEntity findUserInfoByToken() {
        String userId = (String) request.getAttribute("userId");
        return new ResponseEntity<>(ResultCodeBase.CODE_SUCCESS, TipConstBase.OPERATION_GET_SUCCESS, backstageLoginUserService.findUserBaseInfoById(userId));
    }





}
