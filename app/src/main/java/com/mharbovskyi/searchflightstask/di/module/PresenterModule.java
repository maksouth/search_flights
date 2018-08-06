package com.mharbovskyi.searchflightstask.di.module;

import com.mharbovskyi.searchflightstask.datasource.FlightsDataSource;
import com.mharbovskyi.searchflightstask.datasource.StationsDataSource;
import com.mharbovskyi.searchflightstask.presentation.contracts.FlightDetailsContract;
import com.mharbovskyi.searchflightstask.presentation.contracts.FlightListContract;
import com.mharbovskyi.searchflightstask.presentation.contracts.SearchFlightContract;
import com.mharbovskyi.searchflightstask.presentation.contracts.SearchStationContract;
import com.mharbovskyi.searchflightstask.presentation.presenters.FlightDetailsPresenter;
import com.mharbovskyi.searchflightstask.presentation.presenters.FlightListPresenter;
import com.mharbovskyi.searchflightstask.presentation.presenters.SearchFlightPresenter;
import com.mharbovskyi.searchflightstask.presentation.presenters.SearchStationPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module(includes = RemoteDataSourceModule.class)
public abstract class PresenterModule {
    @Binds
    public abstract FlightDetailsContract.Presenter bindFlightDetailsPresenter(FlightDetailsPresenter presenter);

    @Binds
    public abstract FlightListContract.Presenter bindFlightListPresenter(FlightListPresenter presenter);

    @Provides
    public static SearchFlightContract.Presenter provideSearchFlightPresenter(FlightsDataSource flightsDataSource) {
        return new SearchFlightPresenter(flightsDataSource);
    }

    @Provides
    public static SearchStationContract.Presenter provideSearchStationPresenter(StationsDataSource stationsDataSource) {
        return new SearchStationPresenter(stationsDataSource);
    }
}
