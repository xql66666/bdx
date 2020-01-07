package com.bdx.info.service;

import com.bdx.info.dao.InfoDao;
import com.bdx.info.entity.param.InfoAddParam;
import com.bdx.info.entity.po.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author: 磊大大
 * @date: 2019/12/31 14:24
 */
@Service
public class InfoService {

    @Autowired
    private InfoDao infoDao;

    public void addInfo(InfoAddParam infoAddParam, String userId) {
        Info info = new Info();
        info.setInfoName(infoAddParam.getInfoName());
        info.setInfoContent(infoAddParam.getInfoContent());
        info.setInfoUser(userId);
        info.setInfoReplyNum(0);
        info.setInfoGood(0);
        info.setInfoType(Byte.valueOf(infoAddParam.getInfoType()));
        info.setCreateTime(new Date());
        infoDao.save(info);
    }
}
