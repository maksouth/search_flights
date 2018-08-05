package com.mharbovskyi.searchflightstask.presentetion.presenters;

import android.util.Log;

import com.mharbovskyi.searchflightstask.model.FlightDetailsModel;
import com.mharbovskyi.searchflightstask.presentetion.contracts.FlightListContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class FlightListPresenter implements FlightListContract.Presenter {

    private static final String TAG = FlightListPresenter.class.getSimpleName();
    private static final int DEFAULT_MAX_PRICE = 150;

    private FlightListContract.View view;
    private List<FlightDetailsModel> flights;

    @Inject
    public FlightListPresenter(){}

    @Override
    public void setView(FlightListContract.View view) {
        this.view = view;
    }

    @Override
    public void onNewPriceFilter(int value) {
        view.showFlights(filterFlights(value));
    }

    @Override
    public void loadFlights(List<FlightDetailsModel> flights) {
        view.setFilterPrice(DEFAULT_MAX_PRICE);
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

    private List<FlightDetailsModel> filterFlights(int maxPrice) {
        List<FlightDetailsModel> filteredFlights = new ArrayList<>();

        for (FlightDetailsModel flightDetailsModel : flights)
            if( Double.valueOf(flightDetailsModel.getRegularFarePrice()) < maxPrice)
                filteredFlights.add(flightDetailsModel);
            else Log.d(TAG, "Filtered out flight " + flightDetailsModel.getFlightNumber()
                    + " " + flightDetailsModel.getRegularFarePrice() + "("
                    + Double.valueOf(flightDetailsModel.getRegularFarePrice())
                    + "), Max filter price: " + maxPrice);

        return filteredFlights;
    }
}
