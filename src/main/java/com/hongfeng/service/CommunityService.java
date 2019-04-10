package com.hongfeng.service;

import com.hongfeng.domain.Community;
import com.hongfeng.repository.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CommunityService {

    @Autowired
    private CommunityRepository communityRepository;

    @Transactional
    public void insertTwo(){
        Community communityA = new Community();
        communityA.setAccontent("AAA");
        communityA.setActitle("A");
        communityA.setPubTime("xxxx/xx/xx");
        communityRepository.save(communityA);


        Community communityB = new Community();
        communityA.setAccontent("BBB");
        communityA.setActitle("B");
        communityA.setPubTime("yyyy/yy/yy");
        communityRepository.save(communityB);
    }
}
