package com.Group_Srinivasan.scrumservice;

import com.Group_Srinivasan.scrumservice.controller.StartSimulationController;
import com.Group_Srinivasan.scrumservice.model.SprintBacklog;
import com.Group_Srinivasan.scrumservice.service.SprintBacklogService;
import com.Group_Srinivasan.scrumservice.database.SprintBacklogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class StartSimulationTestDevTeam {

    @Mock
    private SprintBacklogService sprintBacklogService;
    @Mock
    private SprintBacklogRepository sprintBacklogRepository;
    @Mock
    private SprintBacklog mockSprintBacklog;

    @InjectMocks
    private StartSimulationController controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDisplayResultsDevTeam() {
        // Arrange
        List<SprintBacklog> sprintBacklogs = new ArrayList<>();
        sprintBacklogs.add(new SprintBacklog()); // You may need to adjust this based on your actual model

        when(sprintBacklogService.getAllSprintBacklog()).thenReturn(sprintBacklogs);

       //Act

    }
}
