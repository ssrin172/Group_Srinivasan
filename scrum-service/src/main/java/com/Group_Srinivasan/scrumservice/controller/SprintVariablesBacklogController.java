package com.Group_Srinivasan.scrumservice.controller;


import com.Group_Srinivasan.scrumservice.model.SprintVariablesBacklog;
import com.Group_Srinivasan.scrumservice.service.SprintVariablesBacklogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/SprintVariablesBacklog")
public class SprintVariablesBacklogController {
    @Autowired
    private SprintVariablesBacklogService sprintVariablesBacklogService;
    @PostMapping("/add")
    public String add(@RequestBody SprintVariablesBacklog sprintVariablesBacklog){
        sprintVariablesBacklogService.saveSprintVariablesBacklog(sprintVariablesBacklog);
        return "New User Story added to SprintVariables Backlog";
    }

    @GetMapping("/getAll")
    public List<SprintVariablesBacklog> getAllSprintVariablesBacklog()  {
        return sprintVariablesBacklogService.getAllSprintVariablesBacklog();
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllSprintVariablesBacklog(){
        sprintVariablesBacklogService.deleteAllSprintVariablesBacklog();
    }


}
