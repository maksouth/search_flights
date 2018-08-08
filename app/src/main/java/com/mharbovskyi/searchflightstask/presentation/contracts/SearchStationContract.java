package com.mharbovskyi.searchflightstask.presentation.contracts;

import com.mharbovskyi.searchflightstask.model.Station;

import java.util.List;

public interface SearchStationContract {
    interface View extends BaseContract.View {
        void loadStations(List<Station> stations);
        String getStationSearchText();
        void goToSearchScreen(Station station);
    }

    interface Presenter extends BaseContract.Presenter {
        void start();
        void searchButtonClicked();
        void stationCLicked(Station station);
    }
}
