package com.Group_Srinivasan.scrumservice.service;

import com.Group_Srinivasan.scrumservice.model.UserStoryBacklog;

import java.util.List;

public interface UserStoryBacklogService {

    public UserStoryBacklog saveUserStory(UserStoryBacklog UserStory);
    public List<UserStoryBacklog> getAllUserStory();

    public void deleteAllUserStory();
}