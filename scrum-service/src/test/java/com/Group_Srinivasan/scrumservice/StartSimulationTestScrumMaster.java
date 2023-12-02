package com.Group_Srinivasan.scrumservice;

import com.Group_Srinivasan.scrumservice.controller.StartSimulationController;
import com.Group_Srinivasan.scrumservice.model.ProductBacklog;
import com.Group_Srinivasan.scrumservice.model.UserStoryBacklog;
import com.Group_Srinivasan.scrumservice.service.ProductBacklogService;
import com.Group_Srinivasan.scrumservice.service.UserStoryBacklogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class StartSimulationTestScrumMaster {

    @Mock
    private ProductBacklogService productBacklogService;

    @Mock
    private UserStoryBacklogService userStoryBacklogService;

    @InjectMocks
    private StartSimulationController controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDisplayResultScrumMaster() {
        // Arrange
        List<UserStoryBacklog> userStoryBacklogs = new ArrayList<>();
        userStoryBacklogs.add(new UserStoryBacklog());

        List<ProductBacklog> productBacklogs = new ArrayList<>();
        // Set a bound of 10 for the ProductBacklog list
        for (int i = 0; i < 10; i++) {
            productBacklogs.add(new ProductBacklog());
        }

        when(userStoryBacklogService.getAllUserStory()).thenReturn(userStoryBacklogs);
        when(productBacklogService.getAllProductBacklog()).thenReturn(productBacklogs);

        // Act
    }
}

