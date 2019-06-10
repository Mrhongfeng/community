package com.stylefeng.guns.modular.system.service;

import com.stylefeng.guns.common.persistence.model.Community;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stylefeng123
 * @since 2019-06-10
 */
public interface ICommunityService extends IService<Community> {
    List<Community> search(@Param("keyword") String keyword);
    List<Community> endlist();
    List<Community> holding();
}
