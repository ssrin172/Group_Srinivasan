package com.Group_Srinivasan.scrumservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/simulate")
public class StartSimulationController {
    @GetMapping("/productOwner")
    public String displayResultProductOwner(){
        return "Result for product owner";
    }

    @GetMapping("/scrumMaster")
    public String displayResultscrumMaster(){ return "Result for scrum master"; }

}
