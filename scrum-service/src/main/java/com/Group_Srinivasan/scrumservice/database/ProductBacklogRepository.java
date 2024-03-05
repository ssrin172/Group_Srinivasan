package com.Group_Srinivasan.scrumservice.database;


import com.Group_Srinivasan.scrumservice.model.ProductBacklog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductBacklogRepository extends JpaRepository<ProductBacklog, Integer> {

}

