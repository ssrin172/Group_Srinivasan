package com.Group_Srinivasan.scrumservice.service;

import com.Group_Srinivasan.scrumservice.model.SprintVariablesBacklog;
import java.util.List;

public interface SprintVariablesBacklogService {
    public SprintVariablesBacklog saveSprintVariablesBacklog(SprintVariablesBacklog sprintVariablesBacklog);
    public List<SprintVariablesBacklog> getAllSprintVariablesBacklog();

    public void deleteAllSprintVariablesBacklog();
}