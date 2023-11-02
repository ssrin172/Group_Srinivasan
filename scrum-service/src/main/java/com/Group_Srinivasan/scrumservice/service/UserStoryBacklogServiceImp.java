package com.Group_Srinivasan.scrumservice.service;

import com.Group_Srinivasan.scrumservice.database.UserStoryBacklogRepository;
import com.Group_Srinivasan.scrumservice.model.UserStoryBacklog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserStoryBacklogServiceImp implements UserStoryBacklogService{
    @Autowired
    private UserStoryBacklogRepository userStoryBacklogRepository;
    @Override
    public UserStoryBacklog saveUserStory(UserStoryBacklog UserStory) {
        return userStoryBacklogRepository.save(UserStory);
    }

    @Override
    public List<UserStoryBacklog> getAllUserStory() {
        return userStoryBacklogRepository.findAll();
    }

    @Override
    public void deleteAllUserStory() {
        userStoryBacklogRepository.deleteAll();
    }
}