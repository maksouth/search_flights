package com.mharbovskyi.searchflightstask.presenter;

import com.mharbovskyi.searchflightstask.R;
import com.mharbovskyi.searchflightstask.datasource.network.FlightNetworkDataSource;
import com.mharbovskyi.searchflightstask.model.SearchRequestModel;
import com.mharbovskyi.searchflightstask.model.Station;

import java.util.Date;

import io.reactivex.disposables.Disposable;

public class SearchFlightPresenter implements SearchFlightContract.Presenter {

    private SearchFlightContract.View view;
    private FlightNetworkDataSource flightDataSource;

    private Disposable flightRequestDisposable;

    private Station origin;
    private Station destination;

    public SearchFlightPresenter(SearchFlightContract.View view,
                                 FlightNetworkDataSource flightDataSource) {
        this.view = view;
        this.flightDataSource = flightDataSource;
    }

    @Override
    public void onNewOrigin(Station origin) {
        this.origin = origin;
        view.setOrigin(origin.getCity());
    }

    @Override
    public void onNewDestination(Station destination) {
        this.destination = destination;
        view.setDestination(destination.getCity());
    }

    @Override
    public void searchButtonClicked() {
        Date departure = view.getDeparture();
        int adults = view.getNumberOfAdults();
        int teens = view.getNumberOfTeens();
        int children = view.getNumberOfChildren();
        if(checkFlightRequestPreconditions(origin, destination, departure, adults, teens, children)) {
            SearchRequestModel.Builder searchRequestBuilder
                    = new SearchRequestModel.Builder(origin.getCode(), destination.getCode(), departure);
            searchRequestBuilder.adults(adults)
                    .teens(teens)
                    .children(children);

            flightRequestDisposable = flightDataSource.searchFlights(searchRequestBuilder.build()).subscribe();
        }
    }

    @Override
    public void originLabelClicked() {

    }

    @Override
    public void destinationLabelClicked() {

    }

    @Override
    public void destroy() {
        if (flightRequestDisposable != null && !flightRequestDisposable.isDisposed())
            flightRequestDisposable.dispose();
        view = null;
    }

    private boolean checkFlightRequestPreconditions(Station origin,
                                                    Station destination,
                                                    Date departure,
                                                    int adults,
                                                    int teens,
                                                    int children) {

        if (origin == null) {
            view.showError(R.string.error_origin_not_specified);
            return false;
        }

        if (destination == null) {
            view.showError(R.string.error_destination_not_specified);
            return false;
        }

        if (departure.before(new Date())) {
            view.showError(R.string.error_wrong_date);
            return false;
        }

        if (adults + teens + children <= 0) {
            view.showError(R.string.error_incorrect_number_of_passengers);
            return false;
        }

        return true;
    }

}
