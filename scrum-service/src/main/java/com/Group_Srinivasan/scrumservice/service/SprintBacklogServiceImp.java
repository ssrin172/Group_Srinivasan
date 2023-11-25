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

    @Override
    public SprintBacklog updateSprintBacklog(SprintBacklog sprintBacklog){
        return sprintBacklogRepository.save(sprintBacklog);
    }

    @Override
    public SprintBacklog getById(int id) {
        SprintBacklog tempSB = null;
        try{
            tempSB = sprintBacklogRepository.findById(id).orElseThrow(RuntimeException::new);
            return tempSB;
        }catch (Exception e){
            System.out.println("Sorry could not find object by id in SPSI(sprint backlog service implementation)" + e);
        }
        return tempSB;
    }


}
