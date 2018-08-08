package com.mharbovskyi.searchflightstask.di.module;

import com.mharbovskyi.searchflightstask.datasource.FlightsDataSource;
import com.mharbovskyi.searchflightstask.datasource.StationsDataSource;
import com.mharbovskyi.searchflightstask.datasource.mock.MockFlightDataSource;
import com.mharbovskyi.searchflightstask.datasource.mock.MockStationsDataSource;

import dagger.Binds;
import dagger.Module;

@Module
public interface MockDataSourceModule {
    @Binds
    FlightsDataSource bindFlightsDataSource(MockFlightDataSource flightDataSource);

    @Binds
    StationsDataSource bindStationsDataSource(MockStationsDataSource stationsDataSource);
}
