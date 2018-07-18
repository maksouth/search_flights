package com.mharbovskyi.searchflightstask.view;

import com.mharbovskyi.searchflightstask.model.FlightDetailsModel;

import java.util.List;

public interface NavigationListeners {

    interface ShowFlightListNavigationListener {
        void goToFlightListScreen(List<FlightDetailsModel> flights);
    }

    interface ShowSearchStationNavigationListener {
        void goToSearchStationScreen();
    }

    interface ShowFlightDetailsNavigationListener {
        void goToFlightDetailsScreen(FlightDetailsModel flightDetailsModel);
    }
}
