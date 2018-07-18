package com.mharbovskyi.searchflightstask.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;

import com.mharbovskyi.searchflightstask.R;
import com.mharbovskyi.searchflightstask.datasource.network.FlightsDataSource;
import com.mharbovskyi.searchflightstask.datasource.network.StationsDataSource;
import com.mharbovskyi.searchflightstask.model.FlightDetailsModel;
import com.mharbovskyi.searchflightstask.model.Station;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity
        implements SearchStationFragment.OnNewStationListener,
        NavigationListeners.ShowFlightListNavigationListener,
        NavigationListeners.ShowSearchStationNavigationListener,
        NavigationListeners.ShowFlightDetailsNavigationListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private FlightsDataSource flightsDataSource;
    private StationsDataSource stationsDataSource;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getFragmentManager();
        stationsDataSource = new StationsDataSource();
        flightsDataSource = new FlightsDataSource();

        stationsDataSource.getStations().subscribe();

        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, new SearchFlightFragment(),
                        SearchFlightFragment.class.getSimpleName())
                .commit();
    }

    public StationsDataSource getStationsDataSource() {
        return stationsDataSource;
    }

    public FlightsDataSource getFlightDataSource() {
        return flightsDataSource;
    }

    @Override
    public void onNewStation(Station station) {

        SearchFlightFragment searchFlightFragment = (SearchFlightFragment)
                fragmentManager.findFragmentByTag(SearchFlightFragment.class.getSimpleName());
        if (searchFlightFragment == null) {
            searchFlightFragment = new SearchFlightFragment();
            Log.d(TAG, "testing, creating new SearchFlightFragment");
        }

        fragmentManager.beginTransaction()
                .show(fragmentManager.findFragmentByTag(SearchFlightFragment.class.getSimpleName()))
                .remove(fragmentManager.findFragmentByTag(SearchStationFragment.class.getSimpleName()))
                .commit();

        searchFlightFragment.onNewStation(station);
        Log.d(TAG, "#onNewStation " + station);
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
    }
}
