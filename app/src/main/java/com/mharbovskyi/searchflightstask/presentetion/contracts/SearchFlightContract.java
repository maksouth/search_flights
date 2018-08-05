package com.mharbovskyi.searchflightstask.presentetion.contracts;

import com.mharbovskyi.searchflightstask.model.FlightDetailsModel;
import com.mharbovskyi.searchflightstask.model.Station;
import com.mharbovskyi.searchflightstask.presentetion.contracts.BaseContract;

import java.util.Date;
import java.util.List;

public interface SearchFlightContract {
    interface View extends BaseContract.View<Presenter> {
        void setOrigin(String text);
        void setDestination(String text);
        Date getDeparture();
        int getNumberOfAdults();
        int getNumberOfTeens();
        int getNumberOfChildren();
        void goToSearchStationScreen();
        void goToFlightResultScreen(List<FlightDetailsModel> flights,
                                    Station origin,
                                    Station destination);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void onNewStation(Station origin);
        void searchButtonClicked();
        void originLabelClicked();
        void destinationLabelClicked();
    }
}
