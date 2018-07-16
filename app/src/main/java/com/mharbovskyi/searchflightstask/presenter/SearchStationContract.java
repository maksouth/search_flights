package com.mharbovskyi.searchflightstask.presenter;

import com.mharbovskyi.searchflightstask.model.Station;

import java.util.List;

public interface SearchStationContract {
    interface View extends BaseContract.View {
        void loadStations(List<Station> stations);
        String getStationSearchText();
        void goToSearchScreen(Station station);
    }

    interface Presenter extends BaseContract.Presenter {
        void searchButtonClicked();
        void stationCLicked(Station station);
    }
}
