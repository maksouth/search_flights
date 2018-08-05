package com.mharbovskyi.searchflightstask.di.component;

import com.mharbovskyi.searchflightstask.datasource.network.StationsDataSource;
import com.mharbovskyi.searchflightstask.di.module.FragmentModule;
import com.mharbovskyi.searchflightstask.di.module.PresenterModule;
import com.mharbovskyi.searchflightstask.view.FlightDetailsFragment;
import com.mharbovskyi.searchflightstask.view.FlightListFragment;
import com.mharbovskyi.searchflightstask.view.SearchFlightFragment;
import com.mharbovskyi.searchflightstask.view.SearchStationFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component (modules = {FragmentModule.class, PresenterModule.class})
public interface FragmentComponent {
    FlightDetailsFragment flightDetailsFragment();
    FlightListFragment flightListFragment();
    SearchFlightFragment searchFlightFragment();
    SearchStationFragment searchStationFragment();
    StationsDataSource stationsDataSource();
}
