package com.Group_Srinivasan.scrumservice;

import com.Group_Srinivasan.scrumservice.database.RandomUserStoryBacklogRepository;
import com.Group_Srinivasan.scrumservice.model.RandomUserStoryBacklog;
import com.Group_Srinivasan.scrumservice.service.RandomUserStoryBacklogServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RandomUserStoryBacklogServiceImpTest {

    @Mock
    private RandomUserStoryBacklogRepository randomUserStoryBacklogRepository;

    @InjectMocks
    private RandomUserStoryBacklogServiceImp randomUserStoryBacklogService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSaveRandomUserStory() {
        RandomUserStoryBacklog userStory = new RandomUserStoryBacklog();
        userStory.setID(1);

        when(randomUserStoryBacklogRepository.save(any(RandomUserStoryBacklog.class))).thenReturn(userStory);

        RandomUserStoryBacklog result = randomUserStoryBacklogService.saveRandomUserStory(userStory);

        assertEquals(userStory, result);
        verify(randomUserStoryBacklogRepository, times(1)).save(any(RandomUserStoryBacklog.class));
    }

    @Test
    void testGetAllRandomUserStory() {
        List<RandomUserStoryBacklog> userStories = Arrays.asList(new RandomUserStoryBacklog(), new RandomUserStoryBacklog());

        when(randomUserStoryBacklogRepository.findAll()).thenReturn(userStories);

        List<RandomUserStoryBacklog> result = randomUserStoryBacklogService.getAllRandomUserStory();

        assertEquals(userStories, result);
        verify(randomUserStoryBacklogRepository, times(1)).findAll();
    }

    @Test
    void testDeleteAllRandomUserStory() {
        randomUserStoryBacklogService.deleteAllRandomUserStory();

        verify(randomUserStoryBacklogRepository, times(1)).deleteAll();
    }
}
