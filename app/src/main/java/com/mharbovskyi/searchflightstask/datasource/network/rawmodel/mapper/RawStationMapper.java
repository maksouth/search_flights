package com.mharbovskyi.searchflightstask.datasource.network.rawmodel.mapper;

import com.mharbovskyi.searchflightstask.datasource.network.rawmodel.stations.Stations;
import com.mharbovskyi.searchflightstask.model.Station;

public class RawStationMapper {
    public static Station apply(Stations stations) {
        return new Station(stations.name, stations.code);
    }
}
