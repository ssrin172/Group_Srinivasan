package com.Group_Srinivasan.scrumservice.database;

import com.Group_Srinivasan.scrumservice.model.RandomUserStoryBacklog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RandomUserStoryBacklogRepository extends JpaRepository<RandomUserStoryBacklog, Integer> {

}

