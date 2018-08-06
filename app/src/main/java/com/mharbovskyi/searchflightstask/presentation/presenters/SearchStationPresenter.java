package com.mharbovskyi.searchflightstask.presentation.presenters;

import android.util.Log;

import com.mharbovskyi.searchflightstask.R;
import com.mharbovskyi.searchflightstask.datasource.StationsDataSource;
import com.mharbovskyi.searchflightstask.model.Station;
import com.mharbovskyi.searchflightstask.presentation.contracts.SearchStationContract;
import com.mharbovskyi.searchflightstask.view.adapters.SearchStationAdapter;

import java.util.LinkedList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class SearchStationPresenter implements SearchStationContract.Presenter {

    private static final String TAG = SearchStationAdapter.class.getSimpleName();

    private SearchStationContract.View view;
    private StationsDataSource stationsDataSource;
    private List<Station> stationList;
    private Disposable disposable;

    public SearchStationPresenter(StationsDataSource stationsDataSource) {
        this.stationsDataSource = stationsDataSource;
    }


    @Override
    public void start() {
        disposable = stationsDataSource.getStations()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(v -> view.showLoading(R.string.loading_stations))
                .doOnTerminate(view::hideLoading)
                .subscribe(stations -> {
                    stationList = stations;
                    view.loadStations(stations);
                }, err -> {
                    Log.d(TAG, err.toString(), err);
                    view.showError(R.string.error_loading_stations);
                });
    }

    @Override
    public void searchButtonClicked() {
        // TODO: 17.07.18 move to rx callable if needed
        String searchText = view.getStationSearchText().trim().toLowerCase();
        List<Station> filteredStationList = new LinkedList<>();
        for (Station station : stationList) {
            if (station.getCity().toLowerCase().contains(searchText)
                    || station.getCode().toLowerCase().contains(searchText)) {
                filteredStationList.add(station);
            }
        }
        view.loadStations(filteredStationList);
    }

    @Override
    public void stationCLicked(Station station) {
        view.goToSearchScreen(station);
    }

    @Override
    public void destroy() {
        if(!disposable.isDisposed())
            disposable.dispose();
        view = null;
        stationsDataSource = null;
    }

    @Override
    public void setView(SearchStationContract.View view) {
        this.view = view;
    }
}
