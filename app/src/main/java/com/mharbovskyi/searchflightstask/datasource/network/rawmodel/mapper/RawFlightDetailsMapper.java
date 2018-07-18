package com.mharbovskyi.searchflightstask.datasource.network.rawmodel.mapper;

import com.mharbovskyi.searchflightstask.datasource.network.rawmodel.flights.Dates;
import com.mharbovskyi.searchflightstask.datasource.network.rawmodel.flights.Fares;
import com.mharbovskyi.searchflightstask.datasource.network.rawmodel.flights.Flights;
import com.mharbovskyi.searchflightstask.datasource.network.rawmodel.flights.RawFlightDetailsModel;
import com.mharbovskyi.searchflightstask.datasource.network.rawmodel.flights.Segments;
import com.mharbovskyi.searchflightstask.datasource.network.rawmodel.flights.Trips;
import com.mharbovskyi.searchflightstask.model.FlightDetailsModel;

import java.util.ArrayList;
import java.util.List;

public class RawFlightDetailsMapper {

    public static List<FlightDetailsModel> apply(RawFlightDetailsModel rawFlightDetailsModel) {

        List<FlightDetailsModel> flightsList = new ArrayList<>();

        for(Trips trip : rawFlightDetailsModel.trips) {
            for (Dates dates : trip.dates)
                for (Flights flights : dates.flights)
                    for (Segments segments : flights.segments)
                        for (Fares fares : flights.regularFare.fares){
                            FlightDetailsModel.Builder builder = new FlightDetailsModel.Builder();
                            builder.currency(rawFlightDetailsModel.currency)
                                    .origin(trip.origin)
                                    .destination(trip.destination)
                                    .fareClass(flights.regularFare.fareClass)
                                    .infantsLeft(flights.infantsLeft)
                                    .flightNumber(flights.flightNumber)
                                    .date(segments.time[0])
                                    .duration(segments.duration)
                                    .regularFarePrice(fares.publishedFare)
                                    .discountInPercent(fares.discountInPercent);
                            flightsList.add(builder.build());
                        }
        }

        return flightsList;
    }
}
