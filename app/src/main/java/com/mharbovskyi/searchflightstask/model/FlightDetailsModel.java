package com.mharbovskyi.searchflightstask.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class FlightDetailsModel implements Parcelable {

    private final Date date;
    private final String flightNumber;
    private final int duration; //in minutes
    private final String currency;
    private final double regularFarePrice;
    private final String origin;
    private final String destination;
    private final int infantsLeft;
    private final String fareClass;
    private final double discountInPercent;

    public FlightDetailsModel(Date date,
                              String flightNumber,
                              int duration,
                              String currency,
                              double regularFarePrice,
                              String origin,
                              String destination,
                              int infantsLeft,
                              String fareClass,
                              double discountInPercent) {
        this.date = date;
        this.flightNumber = flightNumber;
        this.duration = duration;
        this.currency = currency;
        this.regularFarePrice = regularFarePrice;
        this.origin = origin;
        this.destination = destination;
        this.infantsLeft = infantsLeft;
        this.fareClass = fareClass;
        this.discountInPercent = discountInPercent;
    }

    protected FlightDetailsModel(Parcel in) {
        flightNumber = in.readString();
        duration = in.readInt();
        currency = in.readString();
        regularFarePrice = in.readDouble();
        origin = in.readString();
        destination = in.readString();
        infantsLeft = in.readInt();
        fareClass = in.readString();
        discountInPercent = in.readDouble();
        date = (Date) in.readSerializable();
    }

    public static final Creator<FlightDetailsModel> CREATOR = new Creator<FlightDetailsModel>() {
        @Override
        public FlightDetailsModel createFromParcel(Parcel in) {
            return new FlightDetailsModel(in);
        }

        @Override
        public FlightDetailsModel[] newArray(int size) {
            return new FlightDetailsModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(flightNumber);
        dest.writeInt(duration);
        dest.writeString(currency);
        dest.writeDouble(regularFarePrice);
        dest.writeString(origin);
        dest.writeString(destination);
        dest.writeInt(infantsLeft);
        dest.writeString(fareClass);
        dest.writeDouble(discountInPercent);
        dest.writeSerializable(date);
    }
}
