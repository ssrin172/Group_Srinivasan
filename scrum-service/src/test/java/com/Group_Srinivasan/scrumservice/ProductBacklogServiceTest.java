package com.Group_Srinivasan.scrumservice;

//package com.Group_Srinivasan.scrumservice.service;

import com.Group_Srinivasan.scrumservice.database.ProductBacklogRepository;
import com.Group_Srinivasan.scrumservice.model.ProductBacklog;
import com.Group_Srinivasan.scrumservice.service.ProductBacklogServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductBacklogServiceTest {

    @Mock
    private ProductBacklogRepository productBacklogRepository;

    @InjectMocks
    private ProductBacklogServiceImp productBacklogService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveProductBacklog() {
        // Arrange
        ProductBacklog productBacklog = new ProductBacklog();
        when(productBacklogRepository.save(productBacklog)).thenReturn(productBacklog);

        // Act
        ProductBacklog savedProduct = productBacklogService.saveProductBacklog(productBacklog);

        // Assert
        assertEquals(productBacklog, savedProduct);
        verify(productBacklogRepository, times(1)).save(productBacklog);
    }

    @Test
    void testGetAllProductBacklog() {
        // Arrange
        ProductBacklog product1 = new ProductBacklog();
        ProductBacklog product2 = new ProductBacklog();
        when(productBacklogRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        // Act
        List<ProductBacklog> productList = productBacklogService.getAllProductBacklog();

        // Assert
        assertEquals(2, productList.size());
        verify(productBacklogRepository, times(1)).findAll();
    }

    @Test
    void testDeleteAllProductBacklog() {
        // Act
        productBacklogService.deleteAllProductBacklog();

        // Assert
        verify(productBacklogRepository, times(1)).deleteAll();
    }
}


