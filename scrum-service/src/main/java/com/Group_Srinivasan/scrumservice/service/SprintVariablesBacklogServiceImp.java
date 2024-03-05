package com.Group_Srinivasan.scrumservice.service;

import com.Group_Srinivasan.scrumservice.database.SprintVariablesBacklogRepository;
import com.Group_Srinivasan.scrumservice.model.SprintVariablesBacklog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SprintVariablesBacklogServiceImp implements SprintVariablesBacklogService{
    @Autowired
    private SprintVariablesBacklogRepository sprintVariablesBacklogRepository;

    @Override
    public SprintVariablesBacklog saveSprintVariablesBacklog(SprintVariablesBacklog sprintVariablesBacklog) {
        return sprintVariablesBacklogRepository.save(sprintVariablesBacklog);
    }

    @Override
    public List<SprintVariablesBacklog> getAllSprintVariablesBacklog() {
        return sprintVariablesBacklogRepository.findAll();
    }

    @Override
    public void deleteAllSprintVariablesBacklog() {
        sprintVariablesBacklogRepository.deleteAll();
    }
}