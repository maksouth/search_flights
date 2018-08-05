package com.mharbovskyi.searchflightstask.di.module;

import com.mharbovskyi.searchflightstask.presentetion.contracts.FlightDetailsContract;
import com.mharbovskyi.searchflightstask.presentetion.presenters.FlightDetailsPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {
    @Provides
    public FlightDetailsContract.Presenter provideFlightDetailsPresenter() {
        return new FlightDetailsPresenter();
    }
}
