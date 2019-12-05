package com.bdx.manager.service;

import com.bdx.manager.client.UserClient;
import com.bdx.manager.dao.UserDao;
import com.bdx.manager.dao.UserInfoDao;
import com.bdx.manager.entity.param.UserListSearchMap;
import com.bdx.manager.entity.param.UserParam;
import com.bdx.manager.entity.po.User;
import com.bdx.manager.entity.po.UserInfo;
import com.bdx.manager.entity.vo.UserUpdateVO;
import com.bdx.manager.entity.vo.UserVO;
import com.github.wenhao.jpa.Sorts;
import com.github.wenhao.jpa.Specifications;
import constants.IdentityEnum;
import constants.RedisKey;
import constants.UserIsTrueEnum;
import entity.PageRecordsDto;
import entity.vo.LoginUserInfoVO;
import exception.core.BusinessException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import util.DateUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: 磊大大
 * @date: 2019/10/21 18:03
 */
@Service
public class BackstageLoginUserService {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserClient userClient;

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private DateUtil dateUtil;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 退出登录 删除redis中的token
     * @return
     */
    public Boolean logout() {
        String userId = (String) request.getAttribute("userId");
        Boolean flag = redisTemplate.delete(RedisKey.REDIS_TOKEN + userId);
        return flag;
    }


    /**
     * 判断是否为空的函数
     * @return
     */
    public static boolean isNotBlank(Object data) {
        if ("".equals(data) || data == null) {
            return false;
        }
        return true;
    }
    /**
     * 查询所有的用户 包括istrue 0和1  （社团用户）
     * @return
     */
    public PageRecordsDto<UserVO> findAllUser(int currentPage, int pageSize, UserListSearchMap userListSearchMap) {

        Date start = dateUtil.formatDate(userListSearchMap.getStarttime());
        Date end = dateUtil.formatDate(userListSearchMap.getEndtime());
        Specification<User> specification = Specifications.<User>and()
                .eq(StringUtils.isNotBlank(userListSearchMap.getUserPhone()),"userPhone", userListSearchMap.getUserPhone())
                .like(StringUtils.isNotBlank(userListSearchMap.getNickname()), "nickname", "%" + userListSearchMap.getNickname() + "%")
                .gt(isNotBlank(start), "createTime", start)
                .lt(isNotBlank(end), "createTime", end)
                .predicate(Specifications.or()
                .eq("identity", IdentityEnum.MEMBER.getStatus()).eq("identity", IdentityEnum.MANAGE.getStatus()).build())
                .build();

        Sort sort = Sorts.builder()
                .asc("createTime")
                .build();

        Page<User> users = userDao.findAll(specification, new PageRequest(currentPage - 1, pageSize, sort));
        List<User> userList = users.getContent();

        List<UserVO> vo = userList.stream()
                .map(e -> new UserVO(e.getUserId(), e.getNickname(), e.getUserPhone(), e.getIdentity(), e.getIstrue(), e.getComplete(), e.getSalt(), e.getCreateTime(), e.getLastUpdateTime()))
                .collect(Collectors.toList());
        if (vo.size() == 0) {
            throw new BusinessException("查询不到用户");
        }
        PageRecordsDto<UserVO> pageRecordsDto = new PageRecordsDto<>();
        pageRecordsDto.setTotal((int) users.getTotalElements());
        pageRecordsDto.setData(vo);
        return pageRecordsDto;
    }

    /**
     * 查询所有的用户 包括istrue 0和1  （社团外用户）
     * @return
     */
    public PageRecordsDto<UserVO> findAllOtherUser(int currentPage, int pageSize, UserListSearchMap userListSearchMap) {

        Date start = dateUtil.formatDate(userListSearchMap.getStarttime());
        Date end = dateUtil.formatDate(userListSearchMap.getEndtime());

        Specification<User> specification = Specifications.<User>and()
                .eq(StringUtils.isNotBlank(userListSearchMap.getUserPhone()),"userPhone", userListSearchMap.getUserPhone())
                .like(StringUtils.isNotBlank(userListSearchMap.getNickname()), "nickname", "%" + userListSearchMap.getNickname() + "%")
                .gt(isNotBlank(start), "createTime", start)
                .lt(isNotBlank(end), "createTime", end)
                .eq("identity", IdentityEnum.ORDINARY.getStatus())
                .build();

        Sort sort = Sorts.builder()
                .asc("createTime")
                .build();

        Page<User> users = userDao.findAll(specification, new PageRequest(currentPage - 1, pageSize, sort));
        List<User> userList = users.getContent();

        List<UserVO> vo = userList.stream()
                .map(e -> new UserVO(e.getUserId(), e.getNickname(), e.getUserPhone(), e.getIdentity(), e.getIstrue(), e.getComplete(), e.getSalt(), e.getCreateTime(), e.getLastUpdateTime()))
                .collect(Collectors.toList());
        if (vo.size() == 0) {
            throw new BusinessException("查询不到用户");
        }
        PageRecordsDto<UserVO> pageRecordsDto = new PageRecordsDto<>();
        pageRecordsDto.setTotal((int) users.getTotalElements());
        pageRecordsDto.setData(vo);
        return pageRecordsDto;
    }

    /**
     * 修改用户的User信息
     * @param param
     * @return
     */
    public void updateUser(String userId, UserParam param) {

        UserInfo userInfo = userInfoDao.findUserInfoByUserId(userId);
        if (userInfo == null) {
            throw new BusinessException("不存在该用户的信息");
        }
        userInfo.setNickname(param.getNickname());
        userInfo.setLastUpdataTime(new Date());
        //更新userInfo表的信息
        userInfoDao.save(userInfo);

        User user = userDao.findUserByUserId(userId);
        if (user == null) {
            throw new BusinessException("不存在该用户");
        }
        user.setNickname(param.getNickname());
        user.setUserPhone(param.getUserPhone());
        user.setIdentity(param.getIdentity());
        user.setLastUpdateTime(new Date());
        //更新user表的信息
        userDao.save(user);

    }

    /**
     * 根据用户的UserId查找User
     * @param userId
     * @return
     */
    public UserUpdateVO findUserById(String userId) {
        User userByUserId = userDao.findUserByUserId(userId);
        if (userByUserId == null) {
            throw new BusinessException("不存在该用户的信息");
        }
        UserUpdateVO data = new UserUpdateVO();
        data.setNickname(userByUserId.getNickname());
        data.setUserPhone(userByUserId.getUserPhone());
        data.setIdentity(userByUserId.getIdentity());
        data.setIstrue(userByUserId.getIstrue());
        data.setSalt(userByUserId.getSalt());
        return data;
    }

    /**
     * 登录
     * @param userPhone
     * @param password
     * @return
     */
    public User login(String userPhone, String password) {
        User user = userDao.findUserByUserPhone(userPhone);
        if (user == null) {
            return null;
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
     * 获取用户role
     * @param userId
     * @return
     */
    public String findRoleByUserId(String userId) {
        String role = userDao.findRoleByUserId(userId);
        return role;
    }


    /**
     * 用户登录后获取基础信息
     * @param userId
     * @return
     */
    public LoginUserInfoVO findUserBaseInfoById(String userId) {
        UserInfo userInfo = userInfoDao.findUserInfoByUserId(userId);
        if (userInfo == null) {
            throw new BusinessException("查询用户信息为空");
        }
        String roles = userDao.findRoleByUserId(userId);
        LoginUserInfoVO loginUserInfoVO = new LoginUserInfoVO();
        loginUserInfoVO.setName(userInfo.getNickname());
        loginUserInfoVO.setHeadimg(userInfo.getHeadImgUrl());
        loginUserInfoVO.setPhone(userDao.findUserPhoneByUserId(userId));
        loginUserInfoVO.setRoles(roles);
        return loginUserInfoVO;
    }
}
