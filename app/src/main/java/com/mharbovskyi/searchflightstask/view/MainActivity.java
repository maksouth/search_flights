package com.mharbovskyi.searchflightstask.view;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.mharbovskyi.searchflightstask.R;
import com.mharbovskyi.searchflightstask.di.component.DaggerActivityComponent;
import com.mharbovskyi.searchflightstask.model.FlightDetailsModel;
import com.mharbovskyi.searchflightstask.model.Station;
import com.mharbovskyi.searchflightstask.view.fragments.FlightDetailsFragment;
import com.mharbovskyi.searchflightstask.view.fragments.FlightListFragment;
import com.mharbovskyi.searchflightstask.view.fragments.SearchFlightFragment;
import com.mharbovskyi.searchflightstask.view.fragments.SearchStationFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;

public class MainActivity extends Activity
        implements HasFragmentInjector,
        SearchStationFragment.OnNewStationListener,
        NavigationListeners.ShowFlightListNavigationListener,
        NavigationListeners.ShowSearchStationNavigationListener,
        NavigationListeners.ShowFlightDetailsNavigationListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    private FragmentManager fragmentManager;
    private Toolbar toolbar;

    private String temporaryToolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DaggerActivityComponent
                .builder()
                .context(this)
                .build()
                .inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = findViewById(R.id.my_toolbar);
        toolbar.setTitle(R.string.app_title);

        fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, new SearchFlightFragment(),
                        SearchFlightFragment.class.getSimpleName())
                .commit();

        fragmentManager.addOnBackStackChangedListener(() -> {
            Fragment fragment = fragmentManager.findFragmentByTag(FlightListFragment.class.getSimpleName());
            if(fragment != null && fragment.isVisible()) {
                toolbar.setTitle(temporaryToolbarTitle);
            } else toolbar.setTitle(R.string.app_title);
        });
    }

    @Override
    public void onNewStation(Station station) {
        Log.d(TAG, "#onNewStation " + station);

        SearchFlightFragment searchFlightFragment = (SearchFlightFragment)
                fragmentManager.findFragmentByTag(SearchFlightFragment.class.getSimpleName());

        fragmentManager.popBackStackImmediate();
        fragmentManager.beginTransaction()
                .show(fragmentManager.findFragmentByTag(SearchFlightFragment.class.getSimpleName()))
                .commit();

        searchFlightFragment.onNewStation(station);
    }

    @Override
    public void goToFlightListScreen(List<FlightDetailsModel> flights) {
        FlightListFragment fragment = new FlightListFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(FlightListFragment.ARGUMENT_FLIGHTS_LIST, new ArrayList<>(flights));
        fragment.setArguments(bundle);

        fragmentManager.beginTransaction()
                .hide(fragmentManager.findFragmentByTag(SearchFlightFragment.class.getSimpleName()))
                .add(R.id.fragment_container, fragment,
                        FlightListFragment.class.getSimpleName())
                .addToBackStack(FlightListFragment.class.getSimpleName())
                .commit();

        FlightDetailsModel someFlight = flights.get(0);
        temporaryToolbarTitle = someFlight.getOrigin() + "-" + someFlight.getDestination();
    }

    @Override
    public void goToSearchStationScreen() {
        fragmentManager.beginTransaction()
                .hide(fragmentManager.findFragmentByTag(SearchFlightFragment.class.getSimpleName()))
                .add(R.id.fragment_container, new SearchStationFragment(),
                        SearchStationFragment.class.getSimpleName())
                .addToBackStack(SearchStationFragment.class.getSimpleName())
                .commit();
    }

    @Override
    public void goToFlightDetailsScreen(FlightDetailsModel flightDetailsModel) {
        Log.d(TAG, "goToFlightDetailsScreen " + flightDetailsModel);

        FlightDetailsFragment fragment = new FlightDetailsFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(FlightDetailsFragment.ARGUMENT_FLIGHT_DETAILS, flightDetailsModel);
        fragment.setArguments(bundle);

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment, FlightDetailsFragment.class.getSimpleName())
                .addToBackStack(FlightDetailsFragment.class.getSimpleName())
                .commit();
    }

    @Override
    public AndroidInjector<Fragment> fragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}
