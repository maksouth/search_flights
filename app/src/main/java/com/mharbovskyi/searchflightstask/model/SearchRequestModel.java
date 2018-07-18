package com.mharbovskyi.searchflightstask.model;

import java.util.Date;

public class SearchRequestModel {
    private final String origin;
    private final String destination;
    private final Date departure;
    private final int children;
    private final int teens;
    private final int adults;

    private SearchRequestModel(Builder builder) {
        origin = builder.origin;
        destination = builder.destination;
        departure = builder.departure;
        children = builder.children;
        teens = builder.teens;
        adults = builder.adults;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public Date getDeparture() {
        return departure;
    }

    public int getChildren() {
        return children;
    }

    public int getTeens() {
        return teens;
    }

    public int getAdults() {
        return adults;
    }

    public static class Builder {
        private final String origin;
        private final String destination;
        private final Date departure;

        private int children = 0;
        private int teens = 0;
        private int adults = 0;

        public Builder(String origin, String destination, Date departure) {
            this.origin = origin;
            this.destination = destination;
            this.departure = departure;
        }

        public Builder children(int val) {
            if (val > 0) children = val;
            return this;
        }

        public Builder teens(int val) {
            if (val > 0) teens = val;
            return this;
        }

        public Builder adults(int val) {
            if (val > 0) adults = val;
            return this;
        }

        public SearchRequestModel build() {
            return new SearchRequestModel(this);
        }
    }

}
