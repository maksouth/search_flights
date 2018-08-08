package com.mharbovskyi.searchflightstask.di.module;

import com.mharbovskyi.searchflightstask.presentation.contracts.FlightDetailsContract;
import com.mharbovskyi.searchflightstask.presentation.contracts.FlightListContract;
import com.mharbovskyi.searchflightstask.presentation.contracts.SearchFlightContract;
import com.mharbovskyi.searchflightstask.presentation.contracts.SearchStationContract;
import com.mharbovskyi.searchflightstask.view.FlightDetailsFragment;
import com.mharbovskyi.searchflightstask.view.FlightListFragment;
import com.mharbovskyi.searchflightstask.view.SearchFlightFragment;
import com.mharbovskyi.searchflightstask.view.SearchStationFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {

    @Provides
    FlightDetailsFragment provideFlightDetailsFragment(FlightDetailsContract.Presenter presenter) {
        FlightDetailsFragment fragment = new FlightDetailsFragment();
        fragment.setPresenter(presenter);
        return fragment;
    }

    @Provides
    FlightListFragment provideFlightListFragment(FlightListContract.Presenter presenter) {
        FlightListFragment fragment = new FlightListFragment();
        fragment.setPresenter(presenter);
        return fragment;
    }

    @Provides
    SearchFlightFragment provideSearchFlightFragment(/*SearchFlightContract.Presenter presenter*/) {
        SearchFlightFragment fragment = new SearchFlightFragment();
        //fragment.setPresenter(presenter);
        return fragment;
    }

    @Provides
    SearchStationFragment provideSearchStationFragment(SearchStationContract.Presenter presenter) {
        SearchStationFragment fragment = new SearchStationFragment();
        fragment.setPresenter(presenter);
        return fragment;
    }
}
