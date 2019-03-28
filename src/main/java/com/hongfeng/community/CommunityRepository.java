package com.hongfeng.community;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.soap.Text;
import java.util.List;

public interface CommunityRepository extends JpaRepository<Community, String> {

    //通过其他查询
//    public List<Community> findByAge(Integer age);

    public List<Community> findByAccontent(String accontent);
}
