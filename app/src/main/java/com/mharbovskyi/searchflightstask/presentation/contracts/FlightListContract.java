package com.mharbovskyi.searchflightstask.presentation.contracts;

import com.mharbovskyi.searchflightstask.model.FlightDetailsModel;

import java.util.List;

public interface FlightListContract {
    interface View extends BaseContract.View<Presenter> {
        void showFlights(List<FlightDetailsModel> flights);
        void goToFlightDetailsScreen(FlightDetailsModel flightDetailsModel);
        void setCurrency(String currency);
        void setFilterPrice(int filterPrice);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void onNewPriceFilter(int value);
        void loadFlights(List<FlightDetailsModel> flights);
        void flightClicked(FlightDetailsModel flightDetailsModel);
    }
}
