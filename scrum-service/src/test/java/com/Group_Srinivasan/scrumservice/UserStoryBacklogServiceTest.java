package com.Group_Srinivasan.scrumservice;

//package com.Group_Srinivasan.scrumservice.service;

import com.Group_Srinivasan.scrumservice.database.UserStoryBacklogRepository;
import com.Group_Srinivasan.scrumservice.model.UserStoryBacklog;
import com.Group_Srinivasan.scrumservice.service.UserStoryBacklogServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserStoryBacklogServiceTest {

    @Mock
    private UserStoryBacklogRepository userStoryBacklogRepository;

    @InjectMocks
    private UserStoryBacklogServiceImp userStoryBacklogService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUserStory() {
        // Arrange
        UserStoryBacklog userStory = new UserStoryBacklog();
        when(userStoryBacklogRepository.save(userStory)).thenReturn(userStory);

        // Act
        UserStoryBacklog savedUserStory = userStoryBacklogService.saveUserStory(userStory);

        // Assert
        assertEquals(userStory, savedUserStory);
        verify(userStoryBacklogRepository, times(1)).save(userStory);
    }

    @Test
    void testGetAllUserStory() {
        // Arrange
        UserStoryBacklog userStory1 = new UserStoryBacklog();
        UserStoryBacklog userStory2 = new UserStoryBacklog();
        when(userStoryBacklogRepository.findAll()).thenReturn(Arrays.asList(userStory1, userStory2));

        // Act
        List<UserStoryBacklog> userStoryList = userStoryBacklogService.getAllUserStory();

        // Assert
        assertEquals(2, userStoryList.size());
        verify(userStoryBacklogRepository, times(1)).findAll();
    }

    @Test
    void testDeleteAllUserStory() {
        // Act
        userStoryBacklogService.deleteAllUserStory();

        // Assert
        verify(userStoryBacklogRepository, times(1)).deleteAll();
    }
}

