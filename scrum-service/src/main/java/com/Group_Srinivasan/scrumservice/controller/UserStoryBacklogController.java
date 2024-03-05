package com.Group_Srinivasan.scrumservice.controller;


import com.Group_Srinivasan.scrumservice.database.UserStoryBacklogRepository;
import com.Group_Srinivasan.scrumservice.model.UserStoryBacklog;
import com.Group_Srinivasan.scrumservice.service.UserStoryBacklogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/UserStoryBacklog")
public class UserStoryBacklogController {
    @Autowired
    private UserStoryBacklogService UserStoryBacklogService;
    @PostMapping("/add")
    public String add(@RequestBody UserStoryBacklog UserStory){
        UserStoryBacklogService.saveUserStory(UserStory);
        return "New User story is added";
    }

    @GetMapping("/getAll")
    public List<UserStoryBacklog> getAllStudents()  {
        return UserStoryBacklogService.getAllUserStory();
    }
    @DeleteMapping("/deleteAll")
    public void deleteAllUserStory(){
        UserStoryBacklogService.deleteAllUserStory();
    }


}
