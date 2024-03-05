package com.Group_Srinivasan.scrumservice;

import com.Group_Srinivasan.scrumservice.database.BlockerListRepository;
import com.Group_Srinivasan.scrumservice.model.BlockerList;
import com.Group_Srinivasan.scrumservice.service.BlockerListServiceImp;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class BlockerListRepositoryTest {

    @Mock
    private BlockerListRepository blockerListRepository;

    @InjectMocks
    private BlockerListServiceImp blockerListService;

    @Test
    void testSaveBlockerList() {
        // Arrange
        BlockerList blockerList = new BlockerList();
        blockerList.setID(1);

        when(blockerListRepository.save(blockerList)).thenReturn(blockerList);
        when(blockerListRepository.findById(1)).thenReturn(java.util.Optional.of(blockerList));

        // Act
        BlockerList savedBlockerList = blockerListService.saveBlockerList(blockerList);

        // Assert
        assertThat(savedBlockerList).isNotNull();
        assertThat(savedBlockerList.getID()).isEqualTo(1);
        assertThat(blockerListRepository.findById(1)).isPresent();
    }

    // Add more tests for other service methods if needed
}
