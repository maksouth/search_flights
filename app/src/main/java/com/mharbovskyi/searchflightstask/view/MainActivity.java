package com.mharbovskyi.searchflightstask.view;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;

import com.mharbovskyi.searchflightstask.R;
import com.mharbovskyi.searchflightstask.datasource.network.StationsDataSource;
import com.mharbovskyi.searchflightstask.model.Station;

public class MainActivity extends Activity
        implements SearchStationFragment.OnFragmentInteractionListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private StationsDataSource stationsDataSource;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getFragmentManager();

        stationsDataSource = new StationsDataSource();
        stationsDataSource.getStations().subscribe();

        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, new SearchStationFragment(),
                        SearchStationFragment.class.getSimpleName())
                .commit();
    }

    public StationsDataSource getStationsDataSource() {
        return stationsDataSource;
    }

    @Override
    public void onFragmentInteraction(Station station) {
        fragmentManager.beginTransaction()
                .remove(fragmentManager.findFragmentByTag(SearchStationFragment.class.getSimpleName()))
                .commit();
        fragmentManager.popBackStack();
        Log.d(TAG, "#onFragmentInteraction " + station);
    }
}
