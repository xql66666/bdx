package com.bdx.info.service;

import com.bdx.info.dao.InfoDao;
import com.bdx.info.entity.param.InfoAddParam;
import com.bdx.info.entity.po.Info;
import com.bdx.info.entity.vo.InfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    /**
     * 查找资讯列表
     * @param currentPage
     * @return
     */
    public List<InfoVO> findInfoList(int currentPage) {
        List<InfoVO> infoVOLists = infoDao.findInfoList(new PageRequest(currentPage-1, 10));
        return infoVOLists;
    }
}
