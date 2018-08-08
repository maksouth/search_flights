package com.mharbovskyi.searchflightstask.di.module;

import com.mharbovskyi.searchflightstask.di.scope.FragmentScope;
import com.mharbovskyi.searchflightstask.view.fragments.FlightDetailsFragment;
import com.mharbovskyi.searchflightstask.view.fragments.FlightListFragment;
import com.mharbovskyi.searchflightstask.view.fragments.SearchFlightFragment;
import com.mharbovskyi.searchflightstask.view.fragments.SearchStationFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module (includes = PresentersProviderModule.class)
public interface FragmentsProviderModule {
    @FragmentScope
    @ContributesAndroidInjector
    SearchFlightFragment contributesSearchFlightFragment();

    @FragmentScope
    @ContributesAndroidInjector
    FlightDetailsFragment contributesFlightDetailsFragment();

    @FragmentScope
    @ContributesAndroidInjector
    FlightListFragment contributesFlightListFragment();

    @FragmentScope
    @ContributesAndroidInjector
    SearchStationFragment contributesSearchStationFragment();

}
