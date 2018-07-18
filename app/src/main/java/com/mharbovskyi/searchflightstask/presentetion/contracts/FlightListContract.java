package com.mharbovskyi.searchflightstask.presentetion.contracts;

import com.mharbovskyi.searchflightstask.model.FlightDetailsModel;

import java.util.List;

public interface FlightListContract {
    interface View extends BaseContract.View {
        void showFlights(List<FlightDetailsModel> flights);
        void goToFlightDetailsScreen(FlightDetailsModel flightDetailsModel);
    }

    interface Presenter extends BaseContract.Presenter {
        void loadFlights(List<FlightDetailsModel> flights);
        void flightClicked(FlightDetailsModel flightDetailsModel);
    }
}
