package com.mharbovskyi.searchflightstask.di.module;

import com.mharbovskyi.searchflightstask.presentetion.contracts.FlightDetailsContract;
import com.mharbovskyi.searchflightstask.view.FlightDetailsFragment;

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

}
