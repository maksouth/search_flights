package com.mharbovskyi.searchflightstask.di.module;

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

@Module (includes = {ViewBindingModule.class})
public interface PresentersProviderModule {

    @Binds
    SearchFlightContract.Presenter searchFlightPresenter(SearchFlightPresenter searchFlightPresenter);

    @Binds
    FlightDetailsContract.Presenter flightDetailsPresenter(FlightDetailsPresenter presenter);

    @Binds
    FlightListContract.Presenter flightListPresenter(FlightListPresenter presenter);

    @Binds
    SearchStationContract.Presenter searchStationPresenter(SearchStationPresenter presenter);
}
