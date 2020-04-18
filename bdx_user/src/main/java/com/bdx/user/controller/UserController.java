package com.bdx.user.controller;


import com.bdx.user.client.SourceClient;
import com.bdx.user.entity.param.*;
import com.bdx.user.entity.po.User;
import com.bdx.user.service.UserService;
import com.bdx.user.util.AttributeUtil;
import com.bdx.user.util.QiNiuUtil;
import constants.RedisKey;
import constants.ResultCodeBase;
import constants.TipConstBase;
import entity.ResponseEntity;
import entity.vo.LoginUserInfoVO;
import exception.core.BusinessException;
import exception.core.TokenErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import util.AesUtil;
import util.JwtUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author: 磊大大
 * @date: 2019/10/14 16:50
 *
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AesUtil aesUtil;

    @Autowired
    private AttributeUtil attributeUtil;

   // @Autowired
    //private SourceClient sourceClient;


    /**
     * 发送注册短信验证码
     * @param param
     * @return
     */
    @RequestMapping(value = "/sendsms", method = RequestMethod.POST)
    public ResponseEntity sendSms(@RequestBody SendSmsParam param) {
        if ("".equals(param.getPhone()) || param.getPhone() == null){
            return new ResponseEntity(ResultCodeBase.CODE_DATA_IS_NULL, TipConstBase.OPERATION_GET_ERROR);
        }
        userService.sendSms(param.getPhone());
        return new ResponseEntity(ResultCodeBase.CODE_SUCCESS, TipConstBase.OPERATION_GET_SUCCESS);
    }

    /**
     * 发送登录所需的验证码
     * @param param
     * @return
     */
    @RequestMapping(value = "/sendloginsms", method = RequestMethod.POST)
    public ResponseEntity sendLoginSms(@RequestBody SendSmsParam param) {
        userService.sendLoginSms(param.getPhone());
        return new ResponseEntity(ResultCodeBase.CODE_SUCCESS, TipConstBase.OPERATION_GET_SUCCESS);
    }

    /**
     * 注册
     * @param
     * @param user
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity regist(@RequestBody RegisterUserParam user) {
        String checkcodeRedis = (String) redisTemplate.opsForValue().get(RedisKey.REDIS_CHECKCODE + user.getUserPhone());
        if (checkcodeRedis == null || "".equals(checkcodeRedis)) {
            return new ResponseEntity(ResultCodeBase.CODE_DATA_IS_NULL, TipConstBase.OPERATION_GET_CHECKCODE);
        }
        if (!checkcodeRedis.equals(user.getMessageCode())) {
            return new ResponseEntity(ResultCodeBase.CODE_BAD_REQUEST, TipConstBase.OPERATION_ERROR_CHECKCODE);
        }
        userService.add(user);
        return new ResponseEntity(ResultCodeBase.CODE_SUCCESS, TipConstBase.OPERATION_REGIST_SUCCESS);
    }

    /**
     * 密码登陆
     * @param user
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody LoginUserParam user) {

            User userLogin = userService.login(aesUtil.aesDecrypt(user.getUsername()), aesUtil.aesDecrypt(user.getPassword()));
            if (userLogin == null) {
                return new ResponseEntity(ResultCodeBase.CODE_BAD_REQUEST, TipConstBase.OPERATION_LOGIN_ERROR);
            }
            //JWT
            String role = userService.findRoleByUserId(userLogin.getUserId());
            String token = jwtUtil.createJWT(userLogin.getUserId(), userLogin.getUserPhone(), role);
            redisTemplate.opsForValue().set(RedisKey.REDIS_TOKEN + userLogin.getUserId(), token, 16, TimeUnit.HOURS);
            LoginUserInfoVO baseInfoById = userService.findUserBaseInfoById(userLogin.getUserId());
            HashMap<String, Object> map = new HashMap<>();
            map.put("token", token);
            map.put("nickname", baseInfoById.getName());
            map.put("headImg", baseInfoById.getHeadimg());
            return new ResponseEntity<>(ResultCodeBase.CODE_SUCCESS, TipConstBase.OPERATION_LOGIN_SUCCESS, map);

    }

    /**
     * 免密登录
     * @param user
     * @return
     */
    @RequestMapping(value = "/codeLogin", method = RequestMethod.POST)
    public ResponseEntity codeLogin(@RequestBody CodeLoginUserParam user) {

            String logincodeRedis = (String) redisTemplate.opsForValue().get(RedisKey.REDIS_LOGINCODE + aesUtil.aesDecrypt(user.getUsername()));
            if (logincodeRedis == null || "".equals(logincodeRedis)) {
                return new ResponseEntity(ResultCodeBase.CODE_DATA_IS_NULL, TipConstBase.OPERATION_GET_CHECKCODE);
            }
            if (!logincodeRedis.equals(user.getLogincode())) {
                return new ResponseEntity(ResultCodeBase.CODE_BAD_REQUEST, TipConstBase.OPERATION_ERROR_CHECKCODE);
            }
            User userCodeLogin = userService.findUserIdByUserPhone(aesUtil.aesDecrypt(user.getUsername()));
            if (userCodeLogin == null) {
                return new ResponseEntity(ResultCodeBase.CODE_BAD_REQUEST, TipConstBase.OPERATION_PHONE_UNREGISTER);
            }

            //JWT
            String role = userService.findRoleByUserId(userCodeLogin.getUserId());
            String token = jwtUtil.createJWT(userCodeLogin.getUserId(), userCodeLogin.getUserPhone(), role);
            redisTemplate.opsForValue().set(RedisKey.REDIS_TOKEN + userCodeLogin.getUserId(), token, 16, TimeUnit.HOURS);
            LoginUserInfoVO baseInfoById = userService.findUserBaseInfoById(userCodeLogin.getUserId());
            HashMap<String, Object> map = new HashMap<>();
            map.put("token", token);
            map.put("nickname", baseInfoById.getName());
            map.put("headImg", baseInfoById.getHeadimg());
            redisTemplate.delete(RedisKey.REDIS_LOGINCODE + aesUtil.aesDecrypt(user.getUsername()));
            return new ResponseEntity<>(ResultCodeBase.CODE_SUCCESS, TipConstBase.OPERATION_LOGIN_SUCCESS, map);

    }

    /**
     * 获取当前用户的基础信息
     * @return
     */
    @RequestMapping(value = "/userInfo", method = RequestMethod.POST)
    public ResponseEntity findUserInfo() {
        String userId = attributeUtil.getAttributeUserId();
        return new ResponseEntity<>(ResultCodeBase.CODE_SUCCESS, TipConstBase.OPERATION_GET_SUCCESS, userService.findUserInfoById(userId));
    }

    /**
     * 查找用户的信息
     * @param userId
     * @return
     */
    @RequestMapping(value = "/userInfo/{userId}", method = RequestMethod.POST)
    public ResponseEntity findUserInfoById(@PathVariable("userId") String userId) {
        return new ResponseEntity<>(ResultCodeBase.CODE_SUCCESS, TipConstBase.OPERATION_GET_SUCCESS, userService.findUserInfoById(userId));
    }

    /**
     * 退出登录 前端删除保存的token
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity logout() {
        Boolean flag = userService.logout();
        if (!flag) {
            throw new TokenErrorException("退出失败！");
        }
        return new ResponseEntity(ResultCodeBase.CODE_SUCCESS, TipConstBase.OPERATION_LOGOUT_SUCCESS);
    }

    /**
     * 用户修改自己的UserInfo信息 同步User表
     * @param ucInfoParam
     * @return
     */
    @RequestMapping(value = "/updateUcInfo", method = RequestMethod.POST)
    public ResponseEntity updateUcInfo(@RequestBody UcInfoParam ucInfoParam) {
        userService.updateUcInfo(attributeUtil.getAttributeUserId(), ucInfoParam);
        return new ResponseEntity(ResultCodeBase.CODE_SUCCESS, TipConstBase.OPERATION_UPDATE_SUCCESS);
    }


    /**
     * 用户上传更改自己的头像
     * @param multipartFile
     * @return
     */
    @RequestMapping(value = "/uploadHeadImg", method = RequestMethod.POST)
    public ResponseEntity uploadHeadImg(@RequestParam("file") MultipartFile multipartFile) {
        String userId = attributeUtil.getAttributeUserId();
        try {
            InputStream inputStream = multipartFile.getInputStream();
            String headImg = QiNiuUtil.qiniuUploadHeadImg(inputStream);
            if (headImg == null || "".equals(headImg)){
                throw new BusinessException("上传头像失败");
            }
            System.out.println("//////////////////////////");
            System.out.println("//////////////////////////====" + headImg);
            userService.updateUcHeadImg(headImg, userId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(ResultCodeBase.CODE_SUCCESS, TipConstBase.OPERATION_UPDATE_SUCCESS);
    }

    /**
     * 查询用户得头像
     * @return
     */
    @RequestMapping(value = "/findUserHeadImg", method = RequestMethod.POST)
    public ResponseEntity findUserHeadImg() {
        return new ResponseEntity<>(ResultCodeBase.CODE_SUCCESS, TipConstBase.OPERATION_GET_SUCCESS, userService.findUserHeadImg(attributeUtil.getAttributeUserId()));
    }

    /**
     * 修改用户的密码
     * @return
     * 对于所有的aes加密传输的参数都要进行参数封装
     */
    @RequestMapping(value = "/updateUserPassword", method = RequestMethod.POST)
    public ResponseEntity updateUserPassword(@RequestBody UpdatePassParam updatePassParam) {
        String userId = attributeUtil.getAttributeUserId();
        userService.updateUcPass(aesUtil.aesDecrypt(updatePassParam.getPass()), userId);
        return new ResponseEntity(ResultCodeBase.CODE_SUCCESS, TipConstBase.OPERATION_UPDATE_SUCCESS);
    }


    /**
     * 查询用户信息是否完善
     * @return
     */
    @RequestMapping(value = "/findUcComplete", method = RequestMethod.POST)
    public ResponseEntity findUcComplete() {
        String userId = attributeUtil.getAttributeUserId();
        return new ResponseEntity<>(ResultCodeBase.CODE_SUCCESS, TipConstBase.OPERATION_GET_SUCCESS, userService.findUserComplete(userId));
    }

    /**
     * 查询个人发布的资源
     * @return
     */
//    @PostMapping(value = "/findUcMySource")
//    public ResponseEntity findUcMySource() {
//        ResponseEntity response = sourceClient.findMySource();
//        return response;
//    }


}
