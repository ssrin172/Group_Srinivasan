package com.Group_Srinivasan.scrumservice.database;

import com.Group_Srinivasan.scrumservice.model.UserStoryBacklog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStoryBacklogRepository extends JpaRepository<UserStoryBacklog, Integer> {

}

