package com.Group_Srinivasan.scrumservice.controller;

import com.Group_Srinivasan.scrumservice.database.SprintBacklogRepository;
import com.Group_Srinivasan.scrumservice.database.UserStoryBacklogRepository;
import com.Group_Srinivasan.scrumservice.model.ProductBacklog;
import com.Group_Srinivasan.scrumservice.model.SprintBacklog;
import com.Group_Srinivasan.scrumservice.model.SprintVariablesBacklog;
import com.Group_Srinivasan.scrumservice.model.UserStoryBacklog;
import com.Group_Srinivasan.scrumservice.service.ProductBacklogService;
import com.Group_Srinivasan.scrumservice.service.SprintBacklogService;
import com.Group_Srinivasan.scrumservice.service.SprintVariablesBacklogService;
import com.Group_Srinivasan.scrumservice.service.UserStoryBacklogService;
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
    private SprintVariablesBacklogService sprintVariablesBacklogService;
    @Autowired
    private ProductBacklogService productBacklogService;

    @Autowired
    private SprintBacklogService sprintBacklogService;
    @Autowired
    private SprintBacklogRepository sprintBacklogRepository;

    @Autowired
    UserStoryBacklogService userStoryBacklogService;

    @GetMapping("/productOwner")
    public String displayResultProductOwner(){

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
            System.out.println("Scrum master\n" + temPB.getID() + " " + temPB.getBV());
        }
        //set sprint variables

        int sprintlength = random.nextInt(1, 6);
        SprintVariablesBacklog tempsvb = new SprintVariablesBacklog();
        tempsvb.setnumberOfSprint(random.nextInt(1, 6));
        tempsvb.setsprintLength(random.nextInt(2,5));
        sprintVariablesBacklogService.saveSprintVariablesBacklog(tempsvb);


        // ------- automate dev Team ---------
        List<SprintBacklog> sb = sprintBacklogService.getAllSprintBacklog();

        for(int j = 0; j < sb.size(); j++){
            int id = sb.get(j).getID();
            int bv = sb.get(j).getBV();
            SprintBacklog currSprintBacklog = sprintBacklogService.getById(id);
            currSprintBacklog.setBV(bv);
            currSprintBacklog.setStoryPoints(random.nextInt(1,8));
            sprintBacklogRepository.save(currSprintBacklog);
            System.out.println("dev Team \n" + currSprintBacklog.getID() + " " + currSprintBacklog.getBV() + " " + currSprintBacklog.getStoryPoints());
        }

        // show actual results of sprints

        return "result for product owner";
    }

    @GetMapping("/scrumMaster")
    public String displayResultScrumMaster(){
        // automate the product owner
        List<UserStoryBacklog> usb = userStoryBacklogService.getAllUserStory();
        int lenOfUSB = usb.size();


        Random random = new Random();
        int lenOfPB = random.nextInt(1, 6);
        System.out.println(lenOfPB);

        for(int i = 0; i < lenOfPB; i++) {
            ProductBacklog pb = new ProductBacklog();
            // get random story every call and add it to sprint backlog

            UserStoryBacklog tempUSB = usb.get(random.nextInt(0, usb.size()));
            pb.setID(tempUSB.getID());
            pb.setBV(tempUSB.getBV());

            usb.remove(tempUSB);

            productBacklogService.saveProductBacklog(pb);
            System.out.println("Product owner\n" + tempUSB.getID() + " " + tempUSB.getBV());
        }


        return "Result for scrum master";
    }

    @GetMapping("/devTeam")
    public String displayResultsDevTeam(){ return "Result for dev team";}

}
