package com.Group_Srinivasan.scrumservice.database;


import com.Group_Srinivasan.scrumservice.model.SprintVariablesBacklog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SprintVariablesBacklogRepository extends JpaRepository<SprintVariablesBacklog, Integer> {

}

