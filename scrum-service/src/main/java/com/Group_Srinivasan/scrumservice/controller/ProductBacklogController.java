package com.Group_Srinivasan.scrumservice.controller;


import com.Group_Srinivasan.scrumservice.model.ProductBacklog;
import com.Group_Srinivasan.scrumservice.service.ProductBacklogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ProductBacklog")
public class ProductBacklogController {
    @Autowired
    private ProductBacklogService productBacklogService;
    @PostMapping("/add")
    public String add(@RequestBody ProductBacklog productBacklog){
        productBacklogService.saveProductBacklog(productBacklog);
        return "New User Story added to product Backlog";
    }

    @GetMapping("/getAll")
    public List<ProductBacklog> getAllProductBacklog()  {
        return productBacklogService.getAllProductBacklog();
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllProductBacklog(){
        productBacklogService.deleteAllProductBacklog();
    }


}
