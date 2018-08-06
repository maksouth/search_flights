package com.mharbovskyi.searchflightstask.datasource.network;

import com.mharbovskyi.searchflightstask.datasource.StationsDataSource;
import com.mharbovskyi.searchflightstask.datasource.network.rawmodel.mapper.RawStationMapper;
import com.mharbovskyi.searchflightstask.datasource.network.service.StationListService;
import com.mharbovskyi.searchflightstask.model.Station;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class RemoteStationsDataSource implements StationsDataSource
{

    private Observable<List<Station>> stationListObservable;

    public RemoteStationsDataSource(StationListService service) {
        stationListObservable = service.getStations()
                .subscribeOn(Schedulers.io())
                .flatMap(stationList -> Observable.fromArray(stationList.stations))
                .map(RawStationMapper::apply)
                .toList()
                .toObservable()
                .cache();
    }

    public Observable<List<Station>> getStations() {
        return stationListObservable;
    }
}
