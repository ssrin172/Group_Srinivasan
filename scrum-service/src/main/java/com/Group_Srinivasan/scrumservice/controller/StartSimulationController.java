package com.Group_Srinivasan.scrumservice.controller;

import com.Group_Srinivasan.scrumservice.model.ProductBacklog;
import com.Group_Srinivasan.scrumservice.model.SprintBacklog;
import com.Group_Srinivasan.scrumservice.model.SprintVariablesBacklog;
import com.Group_Srinivasan.scrumservice.service.ProductBacklogService;
import com.Group_Srinivasan.scrumservice.service.SprintBacklogService;
import com.Group_Srinivasan.scrumservice.service.SprintVariablesBacklogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/simulate")
public class StartSimulationController {

    @Autowired
    private ProductBacklogService productBacklogService;

    @Autowired
    private SprintBacklogService sprintBacklogService;

    @Autowired
    private SprintVariablesBacklogService sprintVariablesBacklogService;

    @GetMapping("/productOwner")
    public String displayResultProductOwner(){


        // -------- automate scrum master
        List<ProductBacklog> pb = productBacklogService.getAllProductBacklog();
        int lenOfPB = pb.size();

        // ---------- automate scrum master -------------
        Random random = new Random();
        int lenOfSP = random.nextInt(1, lenOfPB+1);
        System.out.println(lenOfSP);

        for(int i = 0; i < lenOfSP; i++) {
            SprintBacklog sp = new SprintBacklog();
            // get random story every call and add it to sprint backlog

            ProductBacklog temPB = pb.get(random.nextInt(0, pb.size()));
            sp.setID(temPB.getID());
            sp.setBV(temPB.getBV());
            sp.setStoryPoints(0);

            pb.remove(temPB);

            sprintBacklogService.saveSprintBacklog(sp);
            System.out.println(temPB.getID() + " " + temPB.getBV());
        }
        //set sprint variables

        int sprintlength = random.nextInt(1, 6);
        SprintVariablesBacklog tempsvb = new SprintVariablesBacklog();
        tempsvb.setnumberOfSprint(random.nextInt(1, 6));
        tempsvb.setsprintLength(random.nextInt(2,5));
        sprintVariablesBacklogService.saveSprintVariablesBacklog(tempsvb);


        // ------- automate developer ---------





        return "result for product owner";
    }

    @GetMapping("/scrumMaster")
    public String displayResultScrumMaster(){ return "Result for scrum master"; }

    @GetMapping("/devTeam")
    public String displayResultsDevTeam(){ return "Result for dev team";}

}
