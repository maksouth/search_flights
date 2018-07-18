package com.mharbovskyi.searchflightstask.presentetion.presenters;

import android.util.Log;

import com.mharbovskyi.searchflightstask.R;
import com.mharbovskyi.searchflightstask.datasource.network.FlightsDataSource;
import com.mharbovskyi.searchflightstask.model.SearchRequestModel;
import com.mharbovskyi.searchflightstask.model.Station;
import com.mharbovskyi.searchflightstask.presentetion.contracts.SearchFlightContract;
import com.mharbovskyi.searchflightstask.view.SearchFlightFragment;

import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class SearchFlightPresenter implements SearchFlightContract.Presenter {

    private static final String TAG = SearchFlightFragment.class.getSimpleName();

    enum StationType { ORIGIN, DESTINATION };

    private SearchFlightContract.View view;
    private FlightsDataSource flightDataSource;

    private Disposable flightRequestDisposable;

    private Station origin;
    private Station destination;

    private StationType selectedStationType;

    public SearchFlightPresenter(SearchFlightContract.View view,
                                 FlightsDataSource flightDataSource) {
        this.view = view;
        this.flightDataSource = flightDataSource;
    }

    @Override
    public void onNewStation(Station station) {
        switch (selectedStationType) {
            case ORIGIN:
                this.origin = station;
                view.setOrigin(origin.getCity());
                break;
            case DESTINATION:
                this.destination = station;
                view.setDestination(destination.getCity());
        }
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

            flightRequestDisposable = flightDataSource.searchFlights(searchRequestBuilder.build())
                    .doOnTerminate(view::hideLoading)
                    .doOnSubscribe(ignored->view.showLoading(R.string.loading_flights))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            flightDetailsModels -> view.goToFlightResultScreen(flightDetailsModels, origin, destination),
                            throwable -> {
                                Log.d(TAG, "Error while retrieving flights", throwable);
                                view.showError(R.string.error_while_flights_search);
                            });
        }
    }

    @Override
    public void originLabelClicked() {
        selectedStationType = StationType.ORIGIN;
        view.goToSearchStationScreen();
    }

    @Override
    public void destinationLabelClicked() {
        selectedStationType = StationType.DESTINATION;
        view.goToSearchStationScreen();
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
