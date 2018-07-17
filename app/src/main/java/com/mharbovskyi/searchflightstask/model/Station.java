package com.mharbovskyi.searchflightstask.model;

public class Station {
    private final String city;
    private final String code;

    public Station(String city, String code) {
        this.city = city;
        this.code = code;
    }

    public String getCity() {
        return city;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Station{" +
                "city='" + city + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
