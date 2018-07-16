package com.mharbovskyi.searchflightstask.datasource.network;

import com.mharbovskyi.searchflightstask.model.Station;

import java.util.List;

import io.reactivex.Observable;

public class StationsDataSource {
    // TODO: 17.07.18 load stations list service, cache observable result
    Observable<List<Station>> getStations() {
        return Observable.empty();
    }
}
