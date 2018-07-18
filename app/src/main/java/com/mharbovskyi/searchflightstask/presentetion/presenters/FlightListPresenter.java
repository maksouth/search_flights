package com.mharbovskyi.searchflightstask.presentetion.presenters;

import com.mharbovskyi.searchflightstask.model.FlightDetailsModel;
import com.mharbovskyi.searchflightstask.presentetion.contracts.FlightListContract;

import java.util.List;

public class FlightListPresenter implements FlightListContract.Presenter {

    private FlightListContract.View view;

    public FlightListPresenter(FlightListContract.View view) {
        this.view = view;
    }

    @Override
    public void loadFlights(List<FlightDetailsModel> flights) {
        if(flights != null) view.showFlights(flights);
    }

    @Override
    public void flightClicked(FlightDetailsModel flightDetailsModel) {
        view.goToFlightDetailsScreen(flightDetailsModel);
    }

    @Override
    public void destroy() {
        view = null;
    }
}
