package com.Group_Srinivasan.scrumservice.service;

import com.Group_Srinivasan.scrumservice.database.ProductBacklogRepository;
import com.Group_Srinivasan.scrumservice.model.ProductBacklog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductBacklogServiceImp implements ProductBacklogService{
    @Autowired
    private ProductBacklogRepository productBacklogRepository;
    @Override
    public ProductBacklog saveProductBacklog(ProductBacklog productBacklog) {
        return productBacklogRepository.save(productBacklog);
    }

    @Override
    public List<ProductBacklog> getAllProductBacklog() {
        return productBacklogRepository.findAll();
    }

    @Override
    public void deleteAllProductBacklog() {
        productBacklogRepository.deleteAll();
    }
}