package com.mharbovskyi.searchflightstask.di.module;

import com.mharbovskyi.searchflightstask.datasource.FlightsDataSource;
import com.mharbovskyi.searchflightstask.datasource.StationsDataSource;
import com.mharbovskyi.searchflightstask.datasource.network.RemoteFlightsDataSource;
import com.mharbovskyi.searchflightstask.datasource.network.RemoteStationsDataSource;
import com.mharbovskyi.searchflightstask.datasource.network.service.FlightService;
import com.mharbovskyi.searchflightstask.datasource.network.service.StationListService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = RetrofitServiceModule.class)
public class RemoteDataSourceModule {

    @Provides
    @Singleton
    public FlightsDataSource provideFlightsDataSource(FlightService flightService) {
        return new RemoteFlightsDataSource(flightService);
    }

    @Provides
    @Singleton
    public StationsDataSource provideStationsDataSource(StationListService service) {
        return new RemoteStationsDataSource(service);
    }

}
