package com.bdx.user.service;

import com.bdx.user.dao.UserDao;
import com.bdx.user.dao.UserInfoDao;
import com.bdx.user.entity.param.RegisterUserParam;
import com.bdx.user.entity.param.UcInfoParam;
import com.bdx.user.entity.po.User;
import com.bdx.user.entity.po.UserInfo;
import com.bdx.user.entity.vo.UserInfoVO;
import com.bdx.user.util.FieldUtil;
import constants.IdentityEnum;
import constants.RabbitmqQueue;
import constants.RedisKey;
import constants.UserIsTrueEnum;
import entity.vo.LoginUserInfoVO;
import exception.core.BusinessException;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import util.AesUtil;
import util.IdWorker;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author: 磊大大
 * @date: 2019/10/14 16:53
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private AesUtil aesUtil;

    /**
     * 获取用户的role
     */
    public String findRoleByUserId(String userId) {
        String role = userDao.findRoleByUserId(userId);
        return role;
    }

    /**
     * 发送验证码
     * @param phone
     */
    public void sendSms(String phone) {

        User user = userDao.findUserByUserPhone(phone);
        if (user != null){
            throw new BusinessException("该用户已被注册");
        }

        //存放用户的发送注册验证码次数，一天最多只能发五条
        Long count = redisTemplate.opsForValue().increment(RedisKey.REDIS_REGISTER_MESSAGE + phone, 1);//设置递增因子
        redisTemplate.expire(RedisKey.REDIS_REGISTER_MESSAGE + phone, 1, TimeUnit.DAYS); //设置时间为1天
        if ( count > 5) {
            throw new BusinessException("该用户今日发送注册码次数过多，请明日再试！");
        }

        //生成六位数字随机数
        String checkcode = RandomStringUtils.randomNumeric(6);
        //向缓存中放一份
        redisTemplate.opsForValue().set(RedisKey.REDIS_CHECKCODE + phone, checkcode, 5, TimeUnit.MINUTES);

        //给用户发一份
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("checkcode", checkcode);
        rabbitTemplate.convertAndSend(RabbitmqQueue.QUEUE_SMS, map);

    }

    /**
     * 发送登录验证码
     * @param phone
     */
    public void sendLoginSms(String phone) {


        //存放用户的发送登录验证码次数，一天最多只能发五条
        Long count = redisTemplate.opsForValue().increment(RedisKey.REDIS_LOGIN_MESSAGE + phone, 1);//设置递增因子
        redisTemplate.expire(RedisKey.REDIS_LOGIN_MESSAGE + phone, 1, TimeUnit.DAYS); //设置时间为1天
        if ( count > 5) {
            throw new BusinessException("该用户今日发送登录码次数过多，请使用密码进行登录！");
        }


        //生成六位数字随机数
        String logincode = RandomStringUtils.randomNumeric(6);
        //向缓存中放一份
        redisTemplate.opsForValue().set(RedisKey.REDIS_LOGINCODE + phone, logincode, 5, TimeUnit.MINUTES);
        //给用户发一份
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("logincode", logincode);
        rabbitTemplate.convertAndSend(RabbitmqQueue.QUEUE_LOGINSMS, map);

    }

    /**
     * 添加用户
     * @param registerUser
     */
    public void add(RegisterUserParam registerUser) {
        if (isUsePhone(registerUser.getUserPhone())) {
            User user = new User();
            user.setUserId( idWorker.nextId() + "");               //用户id
            user.setNickname(registerUser.getUserPhone());         //用户昵称
            user.setUserPhone(registerUser.getUserPhone());        //用户手机号
            user.setPassword(bCryptPasswordEncoder.encode(aesUtil.aesDecrypt(registerUser.getPassword())));  //用户密码
            user.setIdentity(IdentityEnum.ORDINARY.getStatus());   //用户身份
            user.setIstrue((byte) 1);                              //是否停用
            user.setComplete((byte) 0);                            //是否完善基础信息
            user.setSalt("");                                      //保留字段
            user.setCreateTime(new Date());                        //创建时间
            user.setLastUpdateTime(new Date());                    //最后更新时间
            userDao.save(user);
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(user.getUserId());
            userInfo.setNickname(registerUser.getUserPhone());
            addUserInfo(userInfo);
        }else {
            throw new BusinessException("手机号已被注册");
        }

    }

    /**
     * 添加用户基础信息
     * @param userInfo
     */
    public void addUserInfo(UserInfo userInfo) {
        //userInfo.setUserId(userInfo.getUserId());              //用户id
        //userInfo.setNickname("小北默认昵称");                   //用户昵称
        userInfo.setHeadImgUrl("http://q1cw8aki8.bkt.clouddn.com/bdxheadimgb156089d-58fb-4da2-a107-367ec636ef91");                            //用户头像url
        userInfo.setSex((byte) 0);                             //用户性别 默认女
        userInfo.setUserMail("");                              //用户邮箱
        userInfo.setBirthday(null);                            //出生日期
        userInfo.setHomecity("");                              //家乡
        userInfo.setUserQq("");                                //用户qq
        userInfo.setSchool("南阳理工学院");                     //学校  默认南阳理工学院
        userInfo.setGrade(2016);                               //届数
        userInfo.setIntroduce("");                             //个人介绍
        userInfo.setCreateTime(new Date());                    //创建时间
        userInfo.setLastUpdataTime(new Date());                //最后更新时间
        userInfoDao.save(userInfo);
    }

    /**
     * 判断手机号是否被注册
     * @param phone
     * @return
     */
    public boolean isUsePhone(String phone) {
        User user = userDao.findUserByUserPhone(phone);
        if (user == null) {
            return true;
        }
        if (user.getIstrue() == UserIsTrueEnum.NOTRUE.getStatus()) {
            throw new BusinessException("该账号已拉入黑名单");
        }
        return false;
    }

    /**
     * 登陆
     * @param userPhone
     * @param password
     * @return
     */
    public User login(String userPhone, String password) {
        User user = userDao.findUserByUserPhone(userPhone);
        if (user == null) {
            throw new BusinessException("用户名或密码错误");
        }
        if (user.getIstrue() == UserIsTrueEnum.NOTRUE.getStatus()) {
            throw new BusinessException("该账号已被拉入黑名单");
        }
        if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    /**
     * 根据手机号查找用户
     * @param userPhone
     * @return
     */
    public User findUserIdByUserPhone(String userPhone) {
        User user = userDao.findUserByUserPhone(userPhone);
        if (user == null) {
            return null;
        }else {
            if (user.getIstrue() == UserIsTrueEnum.NOTRUE.getStatus()) {
                throw new BusinessException("该账号被拉入黑名单");
            }
            return user;
        }

    }

    /**
     * 根据id查找用户基础信息
     * @param userId
     * @return
     */
    public UserInfoVO findUserInfoById(String userId) {
        UserInfo userInfo = userInfoDao.findUserInfoByUserId(userId);
        if (userInfo == null) {
            throw new BusinessException("查询用户信息为空");
        }
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(userInfo,userInfoVO);
        return userInfoVO;
    }

    /**
     * 退出登录 删除redis中的token
     * @return
     */
    public Boolean logout() {
        String userId = (String) request.getAttribute("userId");
        Boolean flag = redisTemplate.delete(RedisKey.REDIS_TOKEN + userId);
        return flag;
    }

    public LoginUserInfoVO findUserBaseInfoById(String userId) {
        UserInfo userInfo = userInfoDao.findUserInfoByUserId(userId);
        if (userInfo == null) {
            throw new BusinessException("查询用户信息为空");
        }
        LoginUserInfoVO loginUserInfoVO = new LoginUserInfoVO();
        loginUserInfoVO.setName(userInfo.getNickname());
        loginUserInfoVO.setHeadimg(userInfo.getHeadImgUrl());
        return loginUserInfoVO;
    }


    /**
     * 修改用户的信息
     * @param userId
     * @param ucInfoParam
     */
    public void updateUcInfo(String userId, UcInfoParam ucInfoParam) {
        if (StringUtils.isBlank(ucInfoParam.getNickname())) {
            throw new BusinessException("用户昵称不能为空");
        }
        UserInfo userInfoByNickname = userInfoDao.findUserInfoByNickname(ucInfoParam.getNickname());
        if (userInfoByNickname == null || (userInfoByNickname.getNickname().equals(ucInfoParam.getNickname()) && userInfoByNickname.getUserId().equals(userId) )) {
            UserInfo userInfo = userInfoDao.findUserInfoByUserId(userId);
            userInfo.setNickname(ucInfoParam.getNickname());
            userInfo.setUserMail(ucInfoParam.getUserMail());
            userInfo.setSex(ucInfoParam.getSex());
            userInfo.setUserQq(ucInfoParam.getUserQq());
            userInfo.setBirthday(ucInfoParam.getBirthday());
            userInfo.setHomecity(ucInfoParam.getHomecity());
            userInfo.setSchool(ucInfoParam.getSchool());
            userInfo.setGrade(ucInfoParam.getGrade());
            userInfo.setIntroduce(ucInfoParam.getIntroduce());
            userInfo.setLastUpdataTime(new Date());
            userInfoDao.save(userInfo);
            if (FieldUtil.userInfoIsNUll(userInfo)) {
                userDao.updateUserCompleteByUserId((byte) 1, userId);
            }
            userDao.updateUserNicknameByUserId(ucInfoParam.getNickname(), userId);
        }else {
            throw new BusinessException("用户昵称已存在");
        }


    }

    /**
     * 修改用户头像
     * @param headImg
     * @param userId
     */
    public void updateUcHeadImg(String headImg, String userId) {
        userInfoDao.updateUserHeadImgByUserId(headImg, userId);
    }

    /**
     * 查询用户头像
     * @param userId
     * @return
     */
    public String findUserHeadImg(String userId) {
        String headImg = userInfoDao.findUserHeadImgByUserId(userId);
        if (headImg == null || "".equals(headImg)) {
            throw new BusinessException("用户头像查询失败");
        }
        return headImg;
    }

    /**
     * 用户修改自己的密码
     * @param userId
     * @param pass
     */
    public void updateUcPass(String pass, String userId) {
        userDao.updateUserPasswordByUserId(bCryptPasswordEncoder.encode(pass), userId);
    }

    /**
     * 查询用户信息是否完整
     * @param userId
     * @return
     */
    public int findUserComplete(String userId) {
        Byte complete = userDao.findUserCompleteByUserId(userId);
        return complete;
    }
}
