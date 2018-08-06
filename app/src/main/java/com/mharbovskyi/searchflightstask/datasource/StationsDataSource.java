package com.mharbovskyi.searchflightstask.datasource;

import com.mharbovskyi.searchflightstask.model.Station;

import java.util.List;

import io.reactivex.Observable;

public interface StationsDataSource {
    public Observable<List<Station>> getStations();
}
