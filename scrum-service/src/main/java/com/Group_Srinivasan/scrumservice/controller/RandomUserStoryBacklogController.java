package com.Group_Srinivasan.scrumservice.controller;


import com.Group_Srinivasan.scrumservice.model.RandomUserStoryBacklog;
import com.Group_Srinivasan.scrumservice.service.RandomUserStoryBacklogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/RandomUserStoryBacklog")
public class RandomUserStoryBacklogController {
    @Autowired
    private RandomUserStoryBacklogService RandomUserStoryBacklogService;
    @PostMapping("/add")
    public String add(@RequestBody RandomUserStoryBacklog RandomUserStory){
        RandomUserStoryBacklogService.saveRandomUserStory(RandomUserStory);
        return "New User story is added";
    }

    @GetMapping("/getAll")
    public List<RandomUserStoryBacklog> getAllRandomUserStoryBacklog()  {
        return RandomUserStoryBacklogService.getAllRandomUserStory();
    }
    @DeleteMapping("/deleteAll")
    public void deleteAllRandomUserStory(){
        RandomUserStoryBacklogService.deleteAllRandomUserStory();
    }


}
