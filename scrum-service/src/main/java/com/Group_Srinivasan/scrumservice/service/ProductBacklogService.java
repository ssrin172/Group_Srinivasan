package com.Group_Srinivasan.scrumservice.service;

import com.Group_Srinivasan.scrumservice.model.ProductBacklog;
import java.util.List;

public interface ProductBacklogService {
    public ProductBacklog saveProductBacklog(ProductBacklog productBacklog);
    public List<ProductBacklog> getAllProductBacklog();

    public void deleteAllProductBacklog();
}

