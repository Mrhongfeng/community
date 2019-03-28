package com.hongfeng.community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.soap.Text;
import java.util.List;
import java.util.Optional;

@RestController
public class CommunityController {

    @Autowired
    private CommunityRepository communityRepository;

    /**
     * 查询列表
     * @return
     */
    @GetMapping(value = "/community")
    public List<Community> communityList(){
        return communityRepository.findAll();
    }

    /**
     * 添加一个对象
     * @param actitle
     * @param accontent
     * @param pubTime
     * @return
     */
    @PostMapping(value = "/community")
    public Community communityAdd(@RequestParam("actitle") String actitle,
                                  @RequestParam("accontent") String accontent,
                                  @RequestParam("pubTime") String pubTime){
        Community community = new Community();
        community.setActitle(actitle);
        community.setAccontent(accontent);
        community.setPubTime(pubTime);

        return communityRepository.save(community);
    }

    /**
     * 查询一个对象
     * @param id
     * @return
     */
    @GetMapping(value = "/community/{id}")
    public Optional<Community> communityFindOne(@PathVariable("id") Integer id){
//        return communityRepository.findById(id);
        Community community = new Community();
        community.setId(id);
        Example<Community> example = Example.of(community);
        return communityRepository.findOne(example);
    }

    /**
     * 更新对象
     * @param id
     * @param actitle
     * @param accontent
     * @param pubTime
     * @return
     */
    @PutMapping(value = "/community/{id}")
    public Community communityUpdate(@PathVariable("id") Integer id,
                                     @RequestParam("actitle") String actitle,
                                     @RequestParam("accontent") String accontent,
                                     @RequestParam("pubTime") String pubTime){
        Community community = new Community();
        community.setId(id);
        community.setActitle(actitle);
        community.setAccontent(accontent);
        community.setPubTime(pubTime);

        return communityRepository.save(community);
    }

    /**
     * 删除对象
     * @param id
     */
    @DeleteMapping(value = "/community/{id}")
    public void communityDelete(@PathVariable("id") Integer id){
        communityDelete(id);
    }

    /**
     * 通过其他查询
     * @param accontent
     * @return
     */
    @GetMapping(value = "/community/accontent/{accontent}")
    public List<Community> communityListByAccontent(@PathVariable("accontent") String accontent){
        return communityRepository.findByAccontent(accontent);
    }

}
