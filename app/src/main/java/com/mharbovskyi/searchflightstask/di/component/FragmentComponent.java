package com.mharbovskyi.searchflightstask.di.component;

import com.mharbovskyi.searchflightstask.di.module.FragmentModule;
import com.mharbovskyi.searchflightstask.di.module.PresenterModule;
import com.mharbovskyi.searchflightstask.view.FlightDetailsFragment;

import dagger.Component;

@Component (modules = {FragmentModule.class, PresenterModule.class})
public interface FragmentComponent {
    FlightDetailsFragment flightDetailsPresenter();
}
