package com.bdx.sources.service;

import com.bdx.sources.dao.SourceDao;
import com.bdx.sources.dao.UserDao;
import com.bdx.sources.entity.param.SearchSourceParam;
import com.bdx.sources.entity.param.SourceParam;
import com.bdx.sources.entity.po.Source;
import com.bdx.sources.entity.vo.MySourceVO;
import com.bdx.sources.entity.vo.SourceDTO;
import com.bdx.sources.entity.vo.SourceVO;
import com.github.wenhao.jpa.Sorts;
import com.github.wenhao.jpa.Specifications;
import entity.PageRecordsDto;
import exception.core.BusinessException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: 磊大大
 * @date: 2019/12/11 15:12
 */
@Service
public class SourceService {

    @Autowired
    private SourceDao sourceDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private IdWorker idWorker;


    /**
     * 添加资源
     * @param userId
     * @param sourceParama
     */
    public void addSource(String userId, SourceParam sourceParama) {
        System.out.println("id:" + userId);
        System.out.println("名字:" + sourceParama.getSourceName());
        System.out.println("密码:" + sourceParama.getSourcePwd());
        System.out.println("类型:" + sourceParama.getSourceType());
        Integer[] types = sourceParama.getSourceType();
        System.out.println("长度:" + types.length);
        String sourceType = "";
        for (Integer type : types) {
            sourceType = sourceType.concat(type.toString() + ",");
        }
        System.out.println("最终：" + sourceType);
        Source source = new Source();
        source.setSourceId("zy" + idWorker.nextId());
        source.setUserId(userId);
        source.setSourceName(sourceParama.getSourceName());
        source.setSourceUrl(sourceParama.getSourceUrl());
        source.setSourcePwd(sourceParama.getSourcePwd());
        source.setSourceIstrue((byte) 0);
        source.setSourceType(sourceType.substring(0, sourceType.length() - 1));
        sourceDao.saveAndFlush(source);
    }

    /**
     * 查询资源
     * @param currentPage
     * @param pageSize
     * @param searchSourceParam
     * @return
     */
    public PageRecordsDto<SourceVO> findSource(int currentPage, int pageSize, SearchSourceParam searchSourceParam) {

        Specification<Source> specification = Specifications.<Source>and()
                .like(StringUtils.isNotBlank(searchSourceParam.getSourceName()), "sourceName", "%" + searchSourceParam.getSourceName() + "%")
                .eq("sourceIstrue", 1)
                .in(StringUtils.isNotBlank(searchSourceParam.getSelect()), "sourceType", searchSourceParam.getSelect())
                .build();

        Sort sort = Sorts.builder()
                .desc("createTime")
                .build();

        Page<Source> sources = sourceDao.findAll(specification, new PageRequest(currentPage - 1, pageSize, sort));
        List<Source> sourceList = sources.getContent();

        List<SourceVO> vo = sourceList.stream()
                .map(e -> new SourceVO(e.getSourceName(), e.getSourceUrl(), e.getSourcePwd(), e.getSourceType().split(","), userDao.findNickNameByUserId(e.getUserId())))
                .collect(Collectors.toList());
        if (vo.size() == 0) {
            throw new BusinessException("查询不到该资源");
        }
        PageRecordsDto<SourceVO> pageRecordsDto = new PageRecordsDto<>();
        pageRecordsDto.setTotal((int) sources.getTotalElements());
        pageRecordsDto.setData(vo);

        return pageRecordsDto;


    }

    /**
     * 查询个人资源
     * @param userId
     * @return
     */
    public List<MySourceVO> findMySource(String userId) {
        List<SourceDTO> mySourceList = sourceDao.findMySourceList(userId);
        List<MySourceVO> vo = mySourceList.stream()
                .map(e -> new MySourceVO(e.getSourceId(), e.getSourceName(), e.getSourceUrl(), e.getSourcePwd(), e.getSourceType().split(",")))
                .collect(Collectors.toList());
        if (vo.size() == 0) {
            return null;
        }
        return vo;
    }

    /**
     * 根据id删除资源
     * @param sourceId
     */

    public void deleteService(String sourceId) {
        Integer source = sourceDao.deleteMySourceBySourceId(sourceId);
        System.out.println("Id是啥=========" + source);
    }

//    public PageRecordsDto<SourceVO> findSource(int currentPage, int pageSize, SearchSourceParam searchSourceParam) {
//        if ("".equals(searchSourceParam.getSelect()) || "0".equals(searchSourceParam.getSelect())) {
//            List<SourceDTO> sourceList = sourceDao.findSourceBySearchSelect("%" + searchSourceParam.getSourceName() + "%", (currentPage-1) * pageSize, pageSize);
//            List<SourceVO> vo = sourceList.stream()
//               .map(e -> new SourceVO(e.getSourceName(), e.getSourceUrl(), e.getSourcePwd(), e.getSourceType().split(",")/*, e.getNickname()*/))
//               .collect(Collectors.toList());
//            if (vo.size() == 0) {
//                throw new BusinessException("查询不到该资源");
//            }
//            PageRecordsDto<SourceVO> pageRecordsDto = new PageRecordsDto<>();
//            pageRecordsDto.setTotal(vo.size());
//            pageRecordsDto.setData(vo);
//            return pageRecordsDto;
//        }
//        //List<SourceDTO> sourceList = sourceDao.findSourceBySearch(searchSourceParam.getSelect(), "%" + searchSourceParam.getSourceName() + "%", new PageRequest(currentPage-1, pageSize));
//        return null;
//    }


}
