package com.mharbovskyi.searchflightstask.datasource;

import com.mharbovskyi.searchflightstask.model.FlightDetailsModel;
import com.mharbovskyi.searchflightstask.model.SearchRequestModel;

import java.util.List;

import io.reactivex.Observable;

public interface FlightsDataSource {
    Observable<List<FlightDetailsModel>> searchFlights(SearchRequestModel request);
}
