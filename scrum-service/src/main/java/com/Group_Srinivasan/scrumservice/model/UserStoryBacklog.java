package com.Group_Srinivasan.scrumservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserStoryBacklog {
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private int BV;

    public UserStoryBacklog() {
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
}

