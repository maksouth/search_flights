package com.mharbovskyi.searchflightstask.di.module;

import com.mharbovskyi.searchflightstask.datasource.network.service.FlightService;
import com.mharbovskyi.searchflightstask.datasource.network.service.StationListService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = NetworkModule.class)
public class RetrofitServiceModule {

    @Provides
    @Singleton
    public FlightService provideFlightService(Retrofit.Builder builder) {
        Retrofit retrofit = builder.baseUrl(FlightService.BASE_URL)
                .build();
        return retrofit.create(FlightService.class);
    }

    @Provides
    @Singleton
    public StationListService provideStationListService(Retrofit.Builder builder) {
        Retrofit retrofit = builder.baseUrl(StationListService.BASE_URL)
                .build();
        return retrofit.create(StationListService.class);
    }

}
