package com.mharbovskyi.searchflightstask.di.injector_feature.module;

import com.mharbovskyi.searchflightstask.presentation.contracts.SearchFlightContract;
import com.mharbovskyi.searchflightstask.view.SearchFlightFragment;

import dagger.Binds;
import dagger.Module;

@Module
public interface SearchFlightFragmentModule {
    @Binds
    SearchFlightContract.View view(SearchFlightFragment flightFragment);
}
