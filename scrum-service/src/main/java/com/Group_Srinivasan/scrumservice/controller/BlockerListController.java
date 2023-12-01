package com.Group_Srinivasan.scrumservice.controller;


import com.Group_Srinivasan.scrumservice.model.BlockerList;
import com.Group_Srinivasan.scrumservice.service.BlockerListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/BlockerList")
public class BlockerListController {
    @Autowired
    private BlockerListService blockerListService;
    @PostMapping("/add")
    public String add(@RequestBody BlockerList blockerList){
        blockerListService.saveBlockerList(blockerList);
        return "New Blocker added to blocker list";
    }

    @GetMapping("/getAll")
    public List<BlockerList> getAllBlockerList()  {
        return blockerListService.getAllBlockerList();
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllBlockerList(){
        blockerListService.deleteAllBlockerList();
    }


}
