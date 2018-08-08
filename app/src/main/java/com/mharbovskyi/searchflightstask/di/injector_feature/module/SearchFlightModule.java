package com.mharbovskyi.searchflightstask.di.injector_feature.module;

import com.mharbovskyi.searchflightstask.datasource.FlightsDataSource;
import com.mharbovskyi.searchflightstask.presentation.contracts.SearchFlightContract;
import com.mharbovskyi.searchflightstask.presentation.presenters.SearchFlightPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module ()
public interface SearchFlightModule {

    @Binds
    SearchFlightContract.Presenter providePresenter(SearchFlightPresenter searchFlightPresenter);
}
