package com.mharbovskyi.searchflightstask.presentetion.presenters;

import com.mharbovskyi.searchflightstask.model.FlightDetailsModel;
import com.mharbovskyi.searchflightstask.presentetion.contracts.BaseContract;
import com.mharbovskyi.searchflightstask.presentetion.contracts.FlightDetailsContract;

public class FlightDetailsPresenter implements FlightDetailsContract.Presenter {

    private FlightDetailsContract.View view;

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

    @Override
    public void setView(FlightDetailsContract.View view) {
        this.view = view;
    }
}
