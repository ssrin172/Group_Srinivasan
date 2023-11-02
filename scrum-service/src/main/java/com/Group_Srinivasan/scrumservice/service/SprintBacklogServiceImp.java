package com.Group_Srinivasan.scrumservice.service;

import com.Group_Srinivasan.scrumservice.database.SprintBacklogRepository;
import com.Group_Srinivasan.scrumservice.model.SprintBacklog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SprintBacklogServiceImp implements SprintBacklogService{
    @Autowired
    private SprintBacklogRepository sprintBacklogRepository;
    @Override
    public SprintBacklog saveSprintBacklog(SprintBacklog sprintBacklog) {
        return sprintBacklogRepository.save(sprintBacklog);
    }

    @Override
    public List<SprintBacklog> getAllSprintBacklog() {
        return sprintBacklogRepository.findAll();
    }

    @Override
    public void deleteAllSprintBacklog() {
        sprintBacklogRepository.deleteAll();
    }
}
