package com.Group_Srinivasan.scrumservice.service;

import com.Group_Srinivasan.scrumservice.model.SprintBacklog;
import java.util.List;

public interface SprintBacklogService {
    public SprintBacklog saveSprintBacklog(SprintBacklog sprintBacklog);
    public List<SprintBacklog> getAllSprintBacklog();

    public void deleteAllSprintBacklog();

    public SprintBacklog updateSprintBacklog(SprintBacklog sprintBacklog);

    public SprintBacklog getById(int id);

}
