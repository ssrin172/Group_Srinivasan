package com.Group_Srinivasan.scrumservice.database;


import com.Group_Srinivasan.scrumservice.model.SprintBacklog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SprintBacklogRepository extends JpaRepository<SprintBacklog, Integer> {

}
