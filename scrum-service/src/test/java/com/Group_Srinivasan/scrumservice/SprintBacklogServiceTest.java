package com.Group_Srinivasan.scrumservice;

//package com.Group_Srinivasan.scrumservice.service;

import com.Group_Srinivasan.scrumservice.database.SprintBacklogRepository;
import com.Group_Srinivasan.scrumservice.model.SprintBacklog;
import com.Group_Srinivasan.scrumservice.service.SprintBacklogServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SprintBacklogServiceTest {

    @Mock
    private SprintBacklogRepository sprintBacklogRepository;

    @InjectMocks
    private SprintBacklogServiceImp sprintBacklogService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveSprintBacklog() {
        // Arrange
        SprintBacklog sprintBacklog = new SprintBacklog();
        when(sprintBacklogRepository.save(sprintBacklog)).thenReturn(sprintBacklog);

        // Act
        SprintBacklog savedSprintBacklog = sprintBacklogService.saveSprintBacklog(sprintBacklog);

        // Assert
        assertEquals(sprintBacklog, savedSprintBacklog);
        verify(sprintBacklogRepository, times(1)).save(sprintBacklog);
    }

    @Test
    void testGetAllSprintBacklog() {
        // Arrange
        SprintBacklog sprint1 = new SprintBacklog();
        SprintBacklog sprint2 = new SprintBacklog();
        when(sprintBacklogRepository.findAll()).thenReturn(Arrays.asList(sprint1, sprint2));

        // Act
        List<SprintBacklog> sprintList = sprintBacklogService.getAllSprintBacklog();

        // Assert
        assertEquals(2, sprintList.size());
        verify(sprintBacklogRepository, times(1)).findAll();
    }

    @Test
    void testDeleteAllSprintBacklog() {
        // Act
        sprintBacklogService.deleteAllSprintBacklog();

        // Assert
        verify(sprintBacklogRepository, times(1)).deleteAll();
    }

    @Test
    void testUpdateSprintBacklog() {
        // Arrange
        SprintBacklog sprintBacklog = new SprintBacklog();
        when(sprintBacklogRepository.save(sprintBacklog)).thenReturn(sprintBacklog);

        // Act
        SprintBacklog updatedSprintBacklog = sprintBacklogService.updateSprintBacklog(sprintBacklog);

        // Assert
        assertEquals(sprintBacklog, updatedSprintBacklog);
        verify(sprintBacklogRepository, times(1)).save(sprintBacklog);
    }
}

