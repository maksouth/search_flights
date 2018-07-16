package com.mharbovskyi.searchflightstask.presenter;

import com.mharbovskyi.searchflightstask.model.FlightDetailsModel;

import java.util.Date;
import java.util.List;

public interface SearchFlightContract {
    interface View extends BaseContract.View{
        String getOrigin();
        String getDestination();
        void setOrigin(String text);
        void setDestination(String text);
        Date getDeparture();
        String getNumberOfAdults();
        String getNumberOfTeens();
        String getNumberOfChildren();
        void goToFlightResultScreen(List<FlightDetailsModel> flights);
    }

    interface Presenter extends BaseContract.Presenter{
        void searchButtonClicked();
        void originLabelClicked();
        void destinationLabelClicked();
    }
}
