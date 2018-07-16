package com.mharbovskyi.searchflightstask.model;

import java.util.Date;

public class SearchRequestModel {
    private final String origin;
    private final String destination;
    private final Date departure;
    private final short children;
    private final short teens;
    private final short adults;

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

    public short getChildren() {
        return children;
    }

    public short getTeens() {
        return teens;
    }

    public short getAdults() {
        return adults;
    }

    public static class Builder {
        private final String origin;
        private final String destination;
        private final Date departure;

        private short children = 0;
        private short teens = 0;
        private short adults = 0;

        public Builder(String origin, String destination, Date departure) {
            this.origin = origin;
            this.destination = destination;
            this.departure = departure;
        }

        public Builder children(short val) {
            children = val;
            return this;
        }

        public Builder teens(short val) {
            teens = val;
            return this;
        }

        public Builder adults(short val) {
            adults = val;
            return this;
        }

        public SearchRequestModel build() {
            return new SearchRequestModel(this);
        }
    }

}
