package com.stylefeng.guns.common.persistence.dao;

import com.stylefeng.guns.common.persistence.model.Community;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author stylefeng123
 * @since 2019-06-10
 */
public interface CommunityMapper extends BaseMapper<Community> {
    List<Community> search(@Param("keyword") String keyword);
    List<Community> endlist();
    List<Community> holding();
}
