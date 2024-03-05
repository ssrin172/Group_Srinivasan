package com.Group_Srinivasan.scrumservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SprintVariablesBacklog {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sprintLength;

    private int numberOfSprint;

    public SprintVariablesBacklog() {
    }

    public int getsprintLength() {
        return sprintLength;
    }

    public void setsprintLength(int sprintLength) {
        this.sprintLength = sprintLength;
    }

    public int getnumberOfSprint() {
        return numberOfSprint;
    }

    public void setnumberOfSprint(int numberOfSprint) {
        this.numberOfSprint = numberOfSprint;
    }
}


