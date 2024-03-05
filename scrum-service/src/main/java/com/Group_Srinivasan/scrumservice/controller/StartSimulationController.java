package com.Group_Srinivasan.scrumservice.controller;

import com.Group_Srinivasan.scrumservice.database.SprintBacklogRepository;
import com.Group_Srinivasan.scrumservice.model.ProductBacklog;
import com.Group_Srinivasan.scrumservice.model.SprintBacklog;
import com.Group_Srinivasan.scrumservice.model.SprintVariablesBacklog;
import com.Group_Srinivasan.scrumservice.model.UserStoryBacklog;
import com.Group_Srinivasan.scrumservice.service.ProductBacklogService;
import com.Group_Srinivasan.scrumservice.service.SprintBacklogService;
import com.Group_Srinivasan.scrumservice.service.SprintVariablesBacklogService;
import com.Group_Srinivasan.scrumservice.service.UserStoryBacklogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    private UserStoryBacklogService userStoryBacklogService;

    // -------------------- start simulations for each roles --------------------
    @GetMapping("/productOwner")
    public ResponseEntity<List<SprintBacklog>> displayResultProductOwner(){

        automateScrumMaster();
        automateDevTeam();

        // show actual results of sprints
        List<SprintBacklog> result = runSimulator();


        return ResponseEntity.ok(result);
    }

    @GetMapping("/scrumMaster")
    public ResponseEntity<List<SprintBacklog>> displayResultScrumMaster(){

        automateDevTeam();

        List<SprintBacklog> result = runSimulator();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/devTeam")
    public ResponseEntity<List<SprintBacklog>> displayResultsDevTeam(){

        List<SprintBacklog> result =  runSimulator();
        return ResponseEntity.ok(result);
    }


    // -------------------- role-selection mapping funtions ------------------
    @GetMapping("/scrumMasterSelection")
    public String automateProductOwnerSMSelection(){

        automateProductOwner();

        return "Addition of US from user Story pool to Product Backlog";
    }

    @GetMapping("/devTeamSelection")
    public String automateDevTeamPOSMSelection(){
        automateProductOwner();
        automateScrumMaster();
        return "product owner and scrum master are automated";
    }


    // -------------------- Automation functions --------------------
    public void automateProductOwner(){
        // automate the product owner
        List<UserStoryBacklog> usb = userStoryBacklogService.getAllUserStory();
        int lenOfUSB = usb.size();


        Random random = new Random();
        int lenOfPB = random.nextInt(3, lenOfUSB);
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
    }

    public void automateScrumMaster(){
        // ---------- automate scrum master -------------
        List<ProductBacklog> pb = productBacklogService.getAllProductBacklog();
        int lenOfPB = pb.size();
        Random random = new Random();
        int lenOfSP = random.nextInt(3, lenOfPB+1);
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
        tempsvb.setsprintLength(random.nextInt(4,8));
        sprintVariablesBacklogService.saveSprintVariablesBacklog(tempsvb);
    }
    public void automateDevTeam(){
        // ------- automate dev Team ---------
        List<SprintBacklog> sb = sprintBacklogService.getAllSprintBacklog();
        Random random = new Random();
        for(int j = 0; j < sb.size(); j++){
            int id = sb.get(j).getID();
            int bv = sb.get(j).getBV();

            SprintBacklog currSprintBacklog = sprintBacklogService.getById(id);
            currSprintBacklog.setBV(bv);
            currSprintBacklog.setStoryPoints(random.nextInt(1,8));
            currSprintBacklog.setCompleted(false);
            sprintBacklogRepository.save(currSprintBacklog);
            System.out.println("dev Team \n" + currSprintBacklog.getID() + " " + currSprintBacklog.getBV() + " " + currSprintBacklog.getStoryPoints() + " " + currSprintBacklog.isCompleted());
        }

    }

    public List<SprintBacklog> runSimulator(){
        // fetch user stories from sprint backlog.
        List<SprintBacklog> sb = sprintBacklogService.getAllSprintBacklog();

        SprintVariablesBacklog svb = sprintVariablesBacklogService.getAllSprintVariablesBacklog().get(0);
        int length = svb.getsprintLength();
        System.out.println(length);

        int index = 0;
        int rolls = 0;


        System.out.println("ID\t\t" + "BV\t\t" + "StoryPoints\t\t" + "Completed") ;
        for(int i =0; i< sb.size(); i++)
        {
            SprintBacklog temp = sb.get(i);
            System.out.println(temp.getID() + "\t\t" + temp.getBV() + "\t\t\t" + temp.getStoryPoints() + "\t\t" + temp.isCompleted());
        }

        while(true){


            Random random = new Random();
            int dieValue = random.nextInt(1,7);
            rolls++;
            SprintBacklog currentStory = sb.get(index);
            currentStory.setStoryPoints(currentStory.getStoryPoints() - dieValue) ;
            if(currentStory.getStoryPoints() <= 0){
                currentStory.setStoryPoints(0);
                currentStory.setCompleted(true);
                index ++;
            }



            System.out.println("Die Value : " + dieValue);
            System.out.println("Roll # " + rolls);
            System.out.println("ID\t\t" + "BV\t\t" + "StoryPoints\t\t" + "Completed") ;


            for(int i =0; i< sb.size(); i++)
            {
                SprintBacklog temp = sb.get(i);
                System.out.println(temp.getID() + "\t\t" + temp.getBV() + "\t\t\t" + temp.getStoryPoints() + "\t\t" + temp.isCompleted());

            }


            if(rolls >= length || index == sb.size() ){
                break;
            }

        }

        return sb;

        // roll a die (1-6 inclusive)
        // die value is story points completed.
        // apply value to first user story (story points) in sprint backlog.
        // story points = story points - value
        // if story points <= 0 ; then user story is done, move on to the next user story in the sprint  backlog

        // 8 - 4 = 4
        // 4 - 6 = 0

        // sprint cylces
        // number of sprints done
        // number of user stories done
        // number of user stories not done

        // return simulator result in jason as string ?!
        // return String
    }

}
