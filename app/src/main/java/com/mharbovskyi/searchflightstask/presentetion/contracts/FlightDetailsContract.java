package com.mharbovskyi.searchflightstask.presentetion.contracts;

import com.mharbovskyi.searchflightstask.model.FlightDetailsModel;

public interface FlightDetailsContract {
    interface View {
        void setOrigin(String origin);
        void setDestination(String destination);
        void setInfantsLeft(String infantsLeft);
        void setFareClass(String fareClass);
        void setDiscountInPercent(String discountInPercent);
    }

    interface Presenter extends BaseContract.Presenter {
        void loadFlightDetails(FlightDetailsModel flightDetailsModel);
    }
}
