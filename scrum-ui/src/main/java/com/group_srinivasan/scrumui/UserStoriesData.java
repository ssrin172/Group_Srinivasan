package com.group_srinivasan.scrumui;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UserStoriesData {
    public static class DataItem {
        private final String id;
        private final String bvd;

        public DataItem(@JsonProperty("id") String id, @JsonProperty("bvd") String bvd) {
            this.id = id;
            this.bvd = bvd;
        }

        public String getId() {
            return id;
        }

        public String getBvd() {
            return bvd;
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
