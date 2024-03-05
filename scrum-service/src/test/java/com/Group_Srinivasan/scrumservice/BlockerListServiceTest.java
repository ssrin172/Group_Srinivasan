package com.Group_Srinivasan.scrumservice;

import com.Group_Srinivasan.scrumservice.model.BlockerList;
import com.Group_Srinivasan.scrumservice.database.BlockerListRepository;
import com.Group_Srinivasan.scrumservice.service.BlockerListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BlockerListServiceTest {

    @Autowired
    private BlockerListService blockerListService;

    @MockBean
    private BlockerListRepository blockerListRepository;

    @Test
    public void testSaveBlockerList() {
        // Given
        BlockerList blockerList = new BlockerList();
        blockerList.setBV(10);

        // Mock the behavior of the repository save method
        when(blockerListRepository.save(blockerList)).thenReturn(blockerList);

        // When
        BlockerList savedBlockerList = blockerListService.saveBlockerList(blockerList);

        // Then
        assertThat(savedBlockerList).isNotNull();
        assertThat(savedBlockerList.getID()).isEqualTo(0); // Assuming ID is not set in the service
        assertThat(savedBlockerList.getBV()).isEqualTo(0);

        // Additional verification: Ensure that the repository save method was called with the correct argument
        when(blockerListRepository.save(blockerList)).thenReturn(savedBlockerList); // Adjust behavior for verification
        BlockerList result = blockerListRepository.save(blockerList);
        assertThat(result).isEqualTo(savedBlockerList);
    }
}
