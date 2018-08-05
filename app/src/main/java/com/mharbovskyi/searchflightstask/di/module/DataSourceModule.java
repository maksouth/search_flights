package com.mharbovskyi.searchflightstask.di.module;

import com.mharbovskyi.searchflightstask.datasource.network.FlightsDataSource;
import com.mharbovskyi.searchflightstask.datasource.network.StationsDataSource;
import com.mharbovskyi.searchflightstask.datasource.network.service.FlightService;
import com.mharbovskyi.searchflightstask.datasource.network.service.StationListService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = RetrofitServiceModule.class)
public class DataSourceModule {

    @Provides
    @Singleton
    public FlightsDataSource provideFlightsDataSource(FlightService flightService) {
        return new FlightsDataSource(flightService);
    }

    @Provides
    @Singleton
    public StationsDataSource provideStationsDataSource(StationListService service) {
        return new StationsDataSource(service);
    }

}
