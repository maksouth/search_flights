package com.mharbovskyi.searchflightstask.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Station implements Parcelable {
    private final String city;
    private final String code;

    public Station(String city, String code) {
        this.city = city;
        this.code = code;
    }

    protected Station(Parcel in) {
        city = in.readString();
        code = in.readString();
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

    public static final Creator<Station> CREATOR = new Creator<Station>() {
        @Override
        public Station createFromParcel(Parcel in) {
            return new Station(in);
        }

        @Override
        public Station[] newArray(int size) {
            return new Station[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(city);
        dest.writeString(code);
    }
}
