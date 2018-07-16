package com.mharbovskyi.searchflightstask.presenter;

import com.mharbovskyi.searchflightstask.model.Station;

public class SearchStationPresenter implements SearchStationContract.Presenter {

    SearchStationContract.View view;

    public SearchStationPresenter(SearchStationContract.View view) {
        this.view = view;
    }

    @Override
    public void searchButtonClicked() {

    }

    @Override
    public void stationCLicked(Station station) {

    }

    @Override
    public void destroy() {
        view = null;
    }
}
