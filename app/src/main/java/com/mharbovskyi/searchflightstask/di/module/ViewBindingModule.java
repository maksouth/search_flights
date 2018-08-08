package com.mharbovskyi.searchflightstask.di.module;

import com.mharbovskyi.searchflightstask.presentation.contracts.FlightDetailsContract;
import com.mharbovskyi.searchflightstask.presentation.contracts.FlightListContract;
import com.mharbovskyi.searchflightstask.presentation.contracts.SearchFlightContract;
import com.mharbovskyi.searchflightstask.presentation.contracts.SearchStationContract;
import com.mharbovskyi.searchflightstask.view.fragments.FlightDetailsFragment;
import com.mharbovskyi.searchflightstask.view.fragments.FlightListFragment;
import com.mharbovskyi.searchflightstask.view.fragments.SearchFlightFragment;
import com.mharbovskyi.searchflightstask.view.fragments.SearchStationFragment;

import dagger.Binds;
import dagger.Module;

@Module
public interface ViewBindingModule {
    @Binds
    SearchFlightContract.View searchFlightView(SearchFlightFragment fragment);

    @Binds
    SearchStationContract.View searchStationView(SearchStationFragment fragment);

    @Binds
    FlightListContract.View flightListView(FlightListFragment fragment);

    @Binds
    FlightDetailsContract.View flightDetailsView(FlightDetailsFragment fragment);
}
