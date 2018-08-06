package com.mharbovskyi.searchflightstask.datasource.mock;

import com.mharbovskyi.searchflightstask.datasource.FlightsDataSource;
import com.mharbovskyi.searchflightstask.model.FlightDetailsModel;
import com.mharbovskyi.searchflightstask.model.SearchRequestModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import javax.inject.Inject;

import io.reactivex.Observable;

public class MockFlightDataSource implements FlightsDataSource {

    private final SimpleDateFormat departureRequestFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    @Inject
    public MockFlightDataSource(){}

    @Override
    public Observable<List<FlightDetailsModel>> searchFlights(SearchRequestModel request) {
        return Observable.fromCallable(() -> generateFlightList(request));
    }

    private List<FlightDetailsModel> generateFlightList(SearchRequestModel requestModel) {
        List<FlightDetailsModel> flights = new ArrayList<>();
        int size = ThreadLocalRandom.current().nextInt(5, 20);
        FlightDetailsModel flight;

        for (int i = 0; i < size; i++) {
            flight = new FlightDetailsModel.Builder()
                    .flightNumber(UUID.randomUUID().toString().substring(0, 6))
                    .currency("USD")
                    .date(departureRequestFormat.format(requestModel.getDeparture()))
                    .discountInPercent(String.valueOf(ThreadLocalRandom.current().nextInt(15)).concat("%"))
                    .fareClass("Economy")
                    .origin(requestModel.getOrigin())
                    .destination(requestModel.getDestination())
                    .infantsLeft(String.valueOf(ThreadLocalRandom.current().nextInt(5)))
                    .duration("1:" + ThreadLocalRandom.current().nextInt(60))
                    .regularFarePrice(String.valueOf(ThreadLocalRandom.current().nextInt(6, 24) * 10))
                    .build();
            flights.add(flight);
        }

        return flights;
    }
}
