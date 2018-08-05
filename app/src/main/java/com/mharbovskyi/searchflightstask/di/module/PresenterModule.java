package com.mharbovskyi.searchflightstask.di.module;

import com.mharbovskyi.searchflightstask.datasource.network.FlightsDataSource;
import com.mharbovskyi.searchflightstask.datasource.network.StationsDataSource;
import com.mharbovskyi.searchflightstask.presentetion.contracts.FlightDetailsContract;
import com.mharbovskyi.searchflightstask.presentetion.contracts.FlightListContract;
import com.mharbovskyi.searchflightstask.presentetion.contracts.SearchFlightContract;
import com.mharbovskyi.searchflightstask.presentetion.contracts.SearchStationContract;
import com.mharbovskyi.searchflightstask.presentetion.presenters.FlightDetailsPresenter;
import com.mharbovskyi.searchflightstask.presentetion.presenters.FlightListPresenter;
import com.mharbovskyi.searchflightstask.presentetion.presenters.SearchFlightPresenter;
import com.mharbovskyi.searchflightstask.presentetion.presenters.SearchStationPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module(includes = DataSourceModule.class)
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
