package com.mharbovskyi.searchflightstask.presenter;

import com.mharbovskyi.searchflightstask.model.FlightDetailsModel;
import com.mharbovskyi.searchflightstask.model.Station;

import java.util.Date;
import java.util.List;

public interface SearchFlightContract {
    interface View extends BaseContract.View {
        void setOrigin(String text);
        void setDestination(String text);
        Date getDeparture();
        int getNumberOfAdults();
        int getNumberOfTeens();
        int getNumberOfChildren();
        void goToFlightResultScreen(List<FlightDetailsModel> flights);
    }

    interface Presenter extends BaseContract.Presenter {
        void onNewOrigin(Station origin);
        void onNewDestination(Station destination);
        void searchButtonClicked();
        void originLabelClicked();
        void destinationLabelClicked();
    }
}
