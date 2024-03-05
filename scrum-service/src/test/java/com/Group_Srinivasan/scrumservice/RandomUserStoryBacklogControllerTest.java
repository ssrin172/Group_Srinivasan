package com.Group_Srinivasan.scrumservice;

import com.Group_Srinivasan.scrumservice.model.RandomUserStoryBacklog;
import com.Group_Srinivasan.scrumservice.service.RandomUserStoryBacklogService;
import com.Group_Srinivasan.scrumservice.controller.RandomUserStoryBacklogController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RandomUserStoryBacklogControllerTest {

    @Mock
    private RandomUserStoryBacklogService randomUserStoryBacklogService;

    @InjectMocks
    private RandomUserStoryBacklogController randomUserStoryBacklogController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddRandomUserStory() {
        RandomUserStoryBacklog userStory = new RandomUserStoryBacklog();
        userStory.setID(1);

        when(randomUserStoryBacklogService.saveRandomUserStory(any(RandomUserStoryBacklog.class))).thenReturn(userStory);

        String result = randomUserStoryBacklogController.add(userStory);

        assertEquals("New User story is added", result);
        verify(randomUserStoryBacklogService, times(1)).saveRandomUserStory(any(RandomUserStoryBacklog.class));
    }

    @Test
    void testGetAllRandomUserStoryBacklog() {
        List<RandomUserStoryBacklog> userStories = Arrays.asList(new RandomUserStoryBacklog(), new RandomUserStoryBacklog());

        when(randomUserStoryBacklogService.getAllRandomUserStory()).thenReturn(userStories);

        List<RandomUserStoryBacklog> result = randomUserStoryBacklogController.getAllRandomUserStoryBacklog();

        assertEquals(userStories, result);
        verify(randomUserStoryBacklogService, times(1)).getAllRandomUserStory();
    }

    @Test
    void testDeleteAllRandomUserStory() {
        randomUserStoryBacklogController.deleteAllRandomUserStory();

        verify(randomUserStoryBacklogService, times(1)).deleteAllRandomUserStory();
    }
}
