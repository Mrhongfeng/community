package com.hongfeng.repository;

import com.hongfeng.domain.Community;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommunityRepository extends JpaRepository<Community, String> {

    //通过其他查询
//    public List<Community> findByAge(Integer age);

    public List<Community> findByAccontent(String accontent);
}
