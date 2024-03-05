package com.Group_Srinivasan.scrumservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class RandomUserStoryBacklog {
	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	private int BV;

	public RandomUserStoryBacklog() {
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

