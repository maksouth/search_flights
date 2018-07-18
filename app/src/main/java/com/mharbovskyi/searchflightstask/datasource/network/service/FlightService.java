package com.mharbovskyi.searchflightstask.datasource.network.service;

import com.mharbovskyi.searchflightstask.datasource.network.rawmodel.flights.RawFlightDetailsModel;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;

public interface FlightService {

    String BASE_URL = "https://sit-nativeapps.ryanair.com";

    @Headers("Content-type: application/json")
    @GET("api/v3/Availability")
    Observable<RawFlightDetailsModel> getFlights(@QueryMap Map<String, String> params);

    interface SearchFlightsParams {
        String ORIGIN = "origin";
        String DESTINATION = "destination";
        String DATEOUT = "dateout";
        String DATEIN = "datein"; //intentionally not used
        String FLEX_DAYS_BEFORE_OUT = "flexdaysbeforeout";
        String FLEX_DAYS_OUT = "flexdaysout";
        String FLEX_DAYS_BEFORE_IN = "flexdaysbeforein";
        String FLEX_DAYS_IN = "flexdaysbeforein";
        String ADULTS = "adt";
        String TEENS = "teen";
        String CHILDREN = "chd";
    }

}
