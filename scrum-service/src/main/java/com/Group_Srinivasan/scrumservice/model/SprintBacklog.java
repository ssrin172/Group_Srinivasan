package com.Group_Srinivasan.scrumservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SprintBacklog {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    private int BV;

    private boolean completed;
    public int getStoryPoints() {
        return storyPoints;
    }

    public void setStoryPoints(int storyPoints) {
        this.storyPoints = storyPoints;
    }

    private int storyPoints;
    public SprintBacklog() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getBV() {
        return BV;
    }

    public void setBV(int BV) {
        this.BV = BV;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
