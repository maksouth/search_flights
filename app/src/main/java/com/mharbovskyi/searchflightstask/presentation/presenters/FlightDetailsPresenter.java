package com.mharbovskyi.searchflightstask.presentation.presenters;

import com.mharbovskyi.searchflightstask.model.FlightDetailsModel;
import com.mharbovskyi.searchflightstask.presentation.contracts.FlightDetailsContract;

import javax.inject.Inject;

public class FlightDetailsPresenter implements FlightDetailsContract.Presenter {

    private FlightDetailsContract.View view;

    @Inject
    FlightDetailsPresenter(FlightDetailsContract.View view) {
        this.view = view;
    }

    @Override
    public void loadFlightDetails(FlightDetailsModel flightDetailsModel) {
        if (flightDetailsModel != null) {
            view.setDestination(flightDetailsModel.getDestination());
            view.setOrigin(flightDetailsModel.getOrigin());
            view.setFareClass(flightDetailsModel.getFareClass());
            view.setInfantsLeft(flightDetailsModel.getInfantsLeft());
            view.setDiscountInPercent(flightDetailsModel.getDiscountInPercent());
        }
    }

    @Override
    public void destroy() {
        view = null;
    }
}
