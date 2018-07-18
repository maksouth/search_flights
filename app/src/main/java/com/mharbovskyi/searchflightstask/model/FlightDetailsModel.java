package com.mharbovskyi.searchflightstask.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.Objects;

public class FlightDetailsModel implements Parcelable {

    //note: everything is String for simplicity
    private final String date;
    private final String flightNumber;
    private final String duration;
    private final String currency;
    private final String regularFarePrice;
    private final String origin;
    private final String destination;
    private final String infantsLeft;
    private final String fareClass;
    private final String discountInPercent;

    public FlightDetailsModel(String date,
                              String flightNumber,
                              String duration,
                              String currency,
                              String regularFarePrice,
                              String origin,
                              String destination,
                              String infantsLeft,
                              String fareClass,
                              String discountInPercent) {
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
        duration = in.readString();
        currency = in.readString();
        regularFarePrice = in.readString();
        origin = in.readString();
        destination = in.readString();
        infantsLeft = in.readString();
        fareClass = in.readString();
        discountInPercent = in.readString();
        date = in.readString();
    }

    public String getDate() {
        return date;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDuration() {
        return duration;
    }

    public String getCurrency() {
        return currency;
    }

    public String getRegularFarePrice() {
        return regularFarePrice;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getInfantsLeft() {
        return infantsLeft;
    }

    public String getFareClass() {
        return fareClass;
    }

    public String getDiscountInPercent() {
        return discountInPercent;
    }

    public static class Builder {
        private String date;
        private String flightNumber;
        private String duration;
        private String currency;
        private String regularFarePrice;
        private String origin;
        private String destination;
        private String infantsLeft;
        private String fareClass;
        private String discountInPercent;

        public Builder date(String date) {
            this.date = date;
            return this;
        }

        public Builder flightNumber(String flightNumber) {
            this.flightNumber = flightNumber;
            return this;
        }

        public Builder duration(String duration) {
            this.duration = duration;
            return this;
        }

        public Builder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder regularFarePrice(String regularFarePrice) {
            this.regularFarePrice = regularFarePrice;
            return this;
        }

        public Builder origin(String origin) {
            this.origin = origin;
            return this;
        }

        public Builder destination(String destination) {
            this.destination = destination;
            return this;
        }

        public Builder infantsLeft(String infantsLeft) {
            this.infantsLeft = infantsLeft;
            return this;
        }

        public Builder fareClass(String fareClass) {
            this.fareClass = fareClass;
            return this;
        }

        public Builder discountInPercent(String discountInPercent) {
            this.discountInPercent = discountInPercent;
            return this;
        }

        public FlightDetailsModel build() {
            Objects.requireNonNull(date);
            Objects.requireNonNull(flightNumber);
            Objects.requireNonNull(duration);
            Objects.requireNonNull(currency);
            Objects.requireNonNull(regularFarePrice);
            Objects.requireNonNull(origin);
            Objects.requireNonNull(destination);
            Objects.requireNonNull(infantsLeft);
            Objects.requireNonNull(fareClass);
            Objects.requireNonNull(discountInPercent);
            return new FlightDetailsModel(date,
                    flightNumber,
                    duration,
                    currency,
                    regularFarePrice,
                    origin,
                    destination,
                    infantsLeft,
                    fareClass,
                    discountInPercent);
        }
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
        dest.writeString(duration);
        dest.writeString(currency);
        dest.writeString(regularFarePrice);
        dest.writeString(origin);
        dest.writeString(destination);
        dest.writeString(infantsLeft);
        dest.writeString(fareClass);
        dest.writeString(discountInPercent);
        dest.writeString(date);
    }
}
