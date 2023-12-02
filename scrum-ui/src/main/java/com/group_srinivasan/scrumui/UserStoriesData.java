package com.group_srinivasan.scrumui;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UserStoriesData {
    public static class DataItem {
        public final String id;
        public final String bvd;
        public final String stp;

        public final String completed;


        public DataItem(@JsonProperty("completed") String completed, @JsonProperty("id") String id, @JsonProperty("bv") String bvd, @JsonProperty("storyPoints") String stp) {
            this.id = id;
            this.bvd = bvd;
            this.stp = stp;
            this.completed = completed;
        }

        public String getId() {
            return id;
        }
        public String getBvd() {
            return bvd;
        }
        public String getStp() {
            return stp;
        }
        public String getCompleted() {
            return completed;
        }

    }

    private final List<DataItem> data;

    public UserStoriesData(@JsonProperty("data") List<DataItem> data) {
        this.data = data;
    }

    public List<DataItem> getData() {
        return data;
    }
    
}
