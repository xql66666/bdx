package com.bdx.info.dao;

import com.bdx.info.entity.po.Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author: 磊大大
 * @date: 2019/12/31 14:24
 */
public interface InfoDao extends JpaRepository<Info, Integer>, JpaSpecificationExecutor<Info> {
}
