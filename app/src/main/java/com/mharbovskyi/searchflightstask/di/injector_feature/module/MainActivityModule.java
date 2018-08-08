package com.mharbovskyi.searchflightstask.di.injector_feature.module;

import com.mharbovskyi.searchflightstask.di.scope.FragmentScope;
import com.mharbovskyi.searchflightstask.view.SearchFlightFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface MainActivityModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = {SearchFlightFragmentModule.class})
    SearchFlightFragment searchFlightFragment();
}
