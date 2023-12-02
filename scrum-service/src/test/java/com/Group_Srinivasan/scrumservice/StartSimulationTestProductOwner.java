package com.Group_Srinivasan.scrumservice;

import com.Group_Srinivasan.scrumservice.controller.StartSimulationController;
import com.Group_Srinivasan.scrumservice.model.ProductBacklog;
import com.Group_Srinivasan.scrumservice.service.ProductBacklogService;
import com.Group_Srinivasan.scrumservice.service.SprintBacklogService;
import com.Group_Srinivasan.scrumservice.service.SprintVariablesBacklogService;
import com.Group_Srinivasan.scrumservice.service.UserStoryBacklogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class StartSimulationTestProductOwner {

    @Mock
    private SprintVariablesBacklogService sprintVariablesBacklogService;

    @Mock
    private ProductBacklogService productBacklogService;

    @Mock
    private SprintBacklogService sprintBacklogService;

    @Mock
    private UserStoryBacklogService userStoryBacklogService;

    @InjectMocks
    private StartSimulationController controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDisplayResultProductOwner() {
        // Arrange
        List<ProductBacklog> productBacklogs = new ArrayList<>();
        ProductBacklog sampleProductBacklog = new ProductBacklog();
        sampleProductBacklog.setBV(5); // Set a sample business value
        productBacklogs.add(sampleProductBacklog);

        when(productBacklogService.getAllProductBacklog()).thenReturn(productBacklogs);
        when(sprintBacklogService.getAllSprintBacklog()).thenReturn(new ArrayList<>());

        // Act

    }

    // Other test methods...
}