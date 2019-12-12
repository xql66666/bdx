package com.bdx.sources.dao;

import com.bdx.sources.entity.po.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author: 磊大大
 * @date: 2019/12/11 15:09
 */
public interface SourceDao extends JpaRepository<Source, Integer>, JpaSpecificationExecutor<Source> {

}
