package com.mharbovskyi.searchflightstask.datasource.mock;

import com.mharbovskyi.searchflightstask.datasource.StationsDataSource;
import com.mharbovskyi.searchflightstask.model.Station;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class MockStationsDataSource implements StationsDataSource {

    private final List<Station> stations;

    {
        stations = new ArrayList<>();
        stations.add(new Station("Wroclaw", "WRO"));
        stations.add(new Station("Kiev", "IEV"));
        stations.add(new Station("Boryspil", "KBP"));
        stations.add(new Station("New York", "NYC"));
        stations.add(new Station("London", "LND"));
        stations.add(new Station("Berlin", "BER"));
        stations.add(new Station("Los Angeles", "LFX"));
        stations.add(new Station("Rome", "ROM"));
        stations.add(new Station("Paris", "PRS"));

    }

    @Inject
    public MockStationsDataSource(){}

    @Override
    public Observable<List<Station>> getStations() {
        return Observable.just(stations);
    }
}
