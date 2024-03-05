package com.Group_Srinivasan.scrumservice.service;

import com.Group_Srinivasan.scrumservice.model.RandomUserStoryBacklog;

import java.util.List;

public interface RandomUserStoryBacklogService {

    public RandomUserStoryBacklog saveRandomUserStory(RandomUserStoryBacklog RandomUserStory);
    public List<RandomUserStoryBacklog> getAllRandomUserStory();

    public void deleteAllRandomUserStory();
}
