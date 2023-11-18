package com.Group_Srinivasan.scrumservice;

//package com.Group_Srinivasan.scrumservice.service;

import com.Group_Srinivasan.scrumservice.database.SprintVariablesBacklogRepository;
import com.Group_Srinivasan.scrumservice.model.SprintVariablesBacklog;
import com.Group_Srinivasan.scrumservice.service.SprintVariablesBacklogServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SprintVariablesBacklogServiceTest {

    @Mock
    private SprintVariablesBacklogRepository sprintVariablesBacklogRepository;

    @InjectMocks
    private SprintVariablesBacklogServiceImp sprintVariablesBacklogService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveSprintVariablesBacklog() {
        // Arrange
        SprintVariablesBacklog sprintVariablesBacklog = new SprintVariablesBacklog();
        when(sprintVariablesBacklogRepository.save(sprintVariablesBacklog)).thenReturn(sprintVariablesBacklog);

        // Act
        SprintVariablesBacklog savedSprintVariablesBacklog = sprintVariablesBacklogService.saveSprintVariablesBacklog(sprintVariablesBacklog);

        // Assert
        assertEquals(sprintVariablesBacklog, savedSprintVariablesBacklog);
        verify(sprintVariablesBacklogRepository, times(1)).save(sprintVariablesBacklog);
    }

    @Test
    void testGetAllSprintVariablesBacklog() {
        // Arrange
        SprintVariablesBacklog sprintVariables1 = new SprintVariablesBacklog();
        SprintVariablesBacklog sprintVariables2 = new SprintVariablesBacklog();
        when(sprintVariablesBacklogRepository.findAll()).thenReturn(Arrays.asList(sprintVariables1, sprintVariables2));

        // Act
        List<SprintVariablesBacklog> sprintVariablesList = sprintVariablesBacklogService.getAllSprintVariablesBacklog();

        // Assert
        assertEquals(2, sprintVariablesList.size());
        verify(sprintVariablesBacklogRepository, times(1)).findAll();
    }

    @Test
    void testDeleteAllSprintVariablesBacklog() {
        // Act
        sprintVariablesBacklogService.deleteAllSprintVariablesBacklog();

        // Assert
        verify(sprintVariablesBacklogRepository, times(1)).deleteAll();
    }
}
