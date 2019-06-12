package com.stylefeng.guns.modular.system.service.impl;

import com.stylefeng.guns.common.persistence.model.Community;
import com.stylefeng.guns.common.persistence.dao.CommunityMapper;
import com.stylefeng.guns.modular.system.service.ICommunityService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2019-06-10
 */
@Service
public class CommunityServiceImpl extends ServiceImpl<CommunityMapper, Community> implements ICommunityService {
    @Resource
    CommunityMapper communityMapper;
    public List<Community> search(@Param("keyword") String keyword){
        return communityMapper.search(keyword);
    }

    public List<Community> endlist(){
        return communityMapper.endlist();
    }

    public List<Community> holding(){
        return communityMapper.holding();
    }

    @Override
    public Map translation(Community attribute) {
        return (Map) attribute;
    }
}
