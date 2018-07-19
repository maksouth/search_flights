package com.mharbovskyi.searchflightstask.presentetion.presenters;

import android.util.Log;

import com.mharbovskyi.searchflightstask.model.FlightDetailsModel;
import com.mharbovskyi.searchflightstask.presentetion.contracts.FlightListContract;

import java.util.ArrayList;
import java.util.List;

public class FlightListPresenter implements FlightListContract.Presenter {

    private static final String TAG = FlightListPresenter.class.getSimpleName();
    private FlightListContract.View view;
    private List<FlightDetailsModel> flights;

    public FlightListPresenter(FlightListContract.View view) {
        this.view = view;
    }

    @Override
    public void onNewPriceFilter(int value) {
        List<FlightDetailsModel> filteredFlights = new ArrayList<>();

        view.setFilterPrice(String.valueOf(value));
        for (FlightDetailsModel flightDetailsModel : flights)
            if( Double.valueOf(flightDetailsModel.getRegularFarePrice()) < value)
                filteredFlights.add(flightDetailsModel);
            else Log.d(TAG, "Filtered out flight " + flightDetailsModel.getFlightNumber()
                    + " " + flightDetailsModel.getRegularFarePrice() + "("
                    + Double.valueOf(flightDetailsModel.getRegularFarePrice())
                    + "), Max filter price: " + value);

        view.showFlights(filteredFlights);
    }

    @Override
    public void loadFlights(List<FlightDetailsModel> flights) {
        if(flights != null) {
            this.flights = flights;
            view.setCurrency(flights.get(0).getCurrency());
            view.showFlights(flights);
        }
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
