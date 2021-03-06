package com.mharbovskyi.searchflightstask.presentation.presenters;

import android.util.Log;

import com.mharbovskyi.searchflightstask.R;
import com.mharbovskyi.searchflightstask.datasource.FlightsDataSource;
import com.mharbovskyi.searchflightstask.model.FlightDetailsModel;
import com.mharbovskyi.searchflightstask.model.SearchRequestModel;
import com.mharbovskyi.searchflightstask.model.Station;
import com.mharbovskyi.searchflightstask.presentation.contracts.SearchFlightContract;
import com.mharbovskyi.searchflightstask.view.fragments.SearchFlightFragment;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class SearchFlightPresenter implements SearchFlightContract.Presenter {

    private static final String TAG = SearchFlightFragment.class.getSimpleName();

    enum StationType { ORIGIN, DESTINATION }

    private final FlightsDataSource flightDataSource;
    private SearchFlightContract.View view;

    private Disposable flightRequestDisposable;

    private Station origin;
    private Station destination;

    private StationType selectedStationType;

    @Inject
    SearchFlightPresenter(SearchFlightContract.View view, FlightsDataSource flightDataSource) {
        this.flightDataSource = flightDataSource;
        this.view = view;
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
                            this::processNewFlights,
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

    private void processNewFlights(List<FlightDetailsModel> flightDetailsModels) {
        if(flightDetailsModels == null || flightDetailsModels.size() == 0)
            view.showError(R.string.error_no_flights_found);
        else view.goToFlightResultScreen(flightDetailsModels, origin, destination);
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
