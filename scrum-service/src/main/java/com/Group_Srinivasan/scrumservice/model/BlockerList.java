package com.Group_Srinivasan.scrumservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BlockerList {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    private int SOL; //this is the solution to the blocker

    public BlockerList() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getBV() {
        return SOL;
    }

    public void setBV(int BV) {
        this.SOL = SOL;
    }
}


