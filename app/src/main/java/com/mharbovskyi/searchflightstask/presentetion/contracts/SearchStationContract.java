package com.mharbovskyi.searchflightstask.presentetion.contracts;

import com.mharbovskyi.searchflightstask.model.Station;
import com.mharbovskyi.searchflightstask.presentetion.contracts.BaseContract;

import java.util.List;

public interface SearchStationContract {
    interface View extends BaseContract.View<Presenter> {
        void loadStations(List<Station> stations);
        String getStationSearchText();
        void goToSearchScreen(Station station);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void start();
        void searchButtonClicked();
        void stationCLicked(Station station);
    }
}
