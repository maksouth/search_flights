package com.mharbovskyi.searchflightstask.di.module;

import com.mharbovskyi.searchflightstask.datasource.FlightsDataSource;
import com.mharbovskyi.searchflightstask.datasource.StationsDataSource;
import com.mharbovskyi.searchflightstask.datasource.mock.MockFlightDataSource;
import com.mharbovskyi.searchflightstask.datasource.mock.MockStationsDataSource;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class MockDataSourceModule {
    @Binds
    abstract FlightsDataSource bindFlightsDataSource(MockFlightDataSource flightDataSource);

    @Binds
    abstract StationsDataSource bindStationsDataSource(MockStationsDataSource stationsDataSource);
}
