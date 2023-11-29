package com.Group_Srinivasan.scrumservice.controller;


import com.Group_Srinivasan.scrumservice.database.SprintBacklogRepository;
import com.Group_Srinivasan.scrumservice.model.SprintBacklog;
import com.Group_Srinivasan.scrumservice.service.SprintBacklogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/SprintBacklog")
public class SprintBacklogController {
    private final SprintBacklogRepository sprintBacklogRepository;

    public SprintBacklogController(SprintBacklogRepository sprintBacklogRepository)
    {
        this.sprintBacklogRepository = sprintBacklogRepository;
    }

    @Autowired
    private SprintBacklogService sprintBacklogService;
    @PostMapping("/add")
    public String add(@RequestBody SprintBacklog sprintBacklog){
        sprintBacklogService.saveSprintBacklog(sprintBacklog);
        return "New User Story added to product Backlog";
    }

    @GetMapping("/getAll")
    public List<SprintBacklog> getAllSprintBacklog()  {
        return sprintBacklogService.getAllSprintBacklog();
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllSprintBacklog(){
        sprintBacklogService.deleteAllSprintBacklog();
    }

    @PutMapping("/{id}")
    public String updateSprintBacklog(@PathVariable int id, @RequestBody SprintBacklog sprintBacklog) {
        SprintBacklog currentsprintBacklog = sprintBacklogRepository.findById(id).orElseThrow(RuntimeException::new);
        currentsprintBacklog.setStoryPoints(sprintBacklog.getStoryPoints());
        currentsprintBacklog.setID(sprintBacklog.getID());
        currentsprintBacklog.setBV(sprintBacklog.getBV());
        currentsprintBacklog.setCompleted(false);
        currentsprintBacklog = sprintBacklogRepository.save(sprintBacklog);
        return "Story points added to user story";
    }


}
