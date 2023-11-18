package com.Group_Srinivasan.scrumservice.service;

import com.Group_Srinivasan.scrumservice.database.RandomUserStoryBacklogRepository;
import com.Group_Srinivasan.scrumservice.model.RandomUserStoryBacklog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RandomUserStoryBacklogServiceImp implements RandomUserStoryBacklogService{
    @Autowired
    private RandomUserStoryBacklogRepository randomuserStoryBacklogRepository;
    @Override
    public RandomUserStoryBacklog saveRandomUserStory(RandomUserStoryBacklog RandomUserStory) {
        return randomuserStoryBacklogRepository.save(RandomUserStory);
    }

    @Override
    public List<RandomUserStoryBacklog> getAllRandomUserStory() {
        return randomuserStoryBacklogRepository.findAll();
    }



    @Override
    public void deleteAllRandomUserStory() {
        randomuserStoryBacklogRepository.deleteAll();
    }
}
