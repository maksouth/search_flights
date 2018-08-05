package com.mharbovskyi.searchflightstask.presentetion.contracts;

import com.mharbovskyi.searchflightstask.model.FlightDetailsModel;

public interface FlightDetailsContract {
    interface View extends BaseContract.View<Presenter> {
        void setOrigin(String origin);
        void setDestination(String destination);
        void setInfantsLeft(String infantsLeft);
        void setFareClass(String fareClass);
        void setDiscountInPercent(String discountInPercent);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void loadFlightDetails(FlightDetailsModel flightDetailsModel);
    }
}
