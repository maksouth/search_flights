package com.mharbovskyi.searchflightstask.datasource.network.service;

import com.mharbovskyi.searchflightstask.datasource.network.rawmodel.stations.StationList;
import com.mharbovskyi.searchflightstask.datasource.network.rawmodel.stations.Stations;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface StationListService {

    String BASE_URL = "https://tripstest.ryanair.com";

    @GET("static/stations.json")
    Observable<StationList> getStations();

}
