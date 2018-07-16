package com.mharbovskyi.searchflightstask.presenter;

import com.mharbovskyi.searchflightstask.model.FlightDetailsModel;

import java.util.List;

public interface FlightListContract {
    interface View extends BaseContract.View{
        void showFlights(List<FlightDetailsModel> flights);
    }

    interface Presenter extends BaseContract.Presenter {
        void LoadFlights(List<FlightDetailsModel> flights);
    }
}
