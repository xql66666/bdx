package com.bdx.sources.dao;

import com.bdx.sources.entity.po.Source;
import com.bdx.sources.entity.vo.MySourceVO;
import com.bdx.sources.entity.vo.SourceDTO;
import com.bdx.sources.entity.vo.SourceVO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: 磊大大
 * @date: 2019/12/11 15:09
 */
@Component
public interface SourceDao extends JpaRepository<Source, Integer>, JpaSpecificationExecutor<Source> {

    @Query(value = "select new com.bdx.sources.entity.vo.SourceDTO(s.sourceId, s.sourceName, s.sourceUrl, s.sourcePwd, s.sourceType) from Source s where s.userId = ?1 order by s.createTime desc")
    List<SourceDTO> findMySourceList(String userId);

    @Transactional
    @Modifying
    @Query(value = "delete from source where source_id = ?1", nativeQuery = true)
    Integer deleteMySourceBySourceId(String userId);

//    @Query(value = "select new com.bdx.sources.entity.vo.SourceDTO(s.sourceName, s.sourceUrl, s.sourcePwd, s.sourceType, u.nickname) " +
//            "from Source s left join User u on u.userId = s.userId where s.sourceName like ?1 and find_in_set(1,source_type)")
//    List<SourceDTO> findSourceBySearchSelect(String sourceName);

/*
    @Query(value = "select s.source_name, s.source_url, s.source_pwd, s.source_type from source s where s.source_istrue = 1 and s.source_name like ?1 limit ?2,?3", nativeQuery = true)
    List<SourceDTO> findSourceBySearchSelect(String sourceName, int start, int end);
*/


   // List<SourceDTO> findSourceBySearch(@Param("select") String select, @Param("sourceName") String sourceName, PageRequest pageRequest);
}
// and find_in_set(1,source_type)
