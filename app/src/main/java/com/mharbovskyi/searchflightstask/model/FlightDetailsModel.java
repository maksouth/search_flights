package com.mharbovskyi.searchflightstask.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

// TODO: 16.07.18 make final, add builder
public class FlightDetailsModel implements Parcelable {
    private final Date date;
    private final String flightNumber;
    private final int duration; //in minutes
    private final String currency;
    private final double regularFare;
    private final double businessFare;
    private final double leisureFare;

    public FlightDetailsModel(Date date,
                              String flightNumber,
                              int duration,
                              String currency,
                              double regularFare,
                              double businessFare,
                              double leisureFare) {
        this.date = date;
        this.flightNumber = flightNumber;
        this.duration = duration;
        this.currency = currency;
        this.regularFare = regularFare;
        this.businessFare = businessFare;
        this.leisureFare = leisureFare;
    }

    private FlightDetailsModel(Parcel in) {
        flightNumber = in.readString();
        duration = in.readInt();
        currency = in.readString();
        regularFare = in.readDouble();
        businessFare = in.readDouble();
        leisureFare = in.readDouble();
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
        dest.writeDouble(regularFare);
        dest.writeDouble(businessFare);
        dest.writeDouble(leisureFare);
        dest.writeSerializable(date);
    }
}
