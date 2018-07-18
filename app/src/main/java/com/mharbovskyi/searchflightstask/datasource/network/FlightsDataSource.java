package com.mharbovskyi.searchflightstask.datasource.network;

import android.util.Log;

import com.mharbovskyi.searchflightstask.datasource.network.rawmodel.flights.RawFlightDetailsModel;
import com.mharbovskyi.searchflightstask.datasource.network.rawmodel.mapper.RawFlightDetailsMapper;
import com.mharbovskyi.searchflightstask.datasource.network.service.FlightService;
import com.mharbovskyi.searchflightstask.model.FlightDetailsModel;
import com.mharbovskyi.searchflightstask.model.SearchRequestModel;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class FlightsDataSource {

    private static final String TAG = FlightsDataSource.class.getSimpleName();

    private final SimpleDateFormat departureRequestFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final Retrofit retrofit;
    private final FlightService flightService;

    public FlightsDataSource() {
        // TODO: 16.07.18 move to gradle singleton
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(FlightService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

        flightService = retrofit.create(FlightService.class);
    }

    public Observable<List<FlightDetailsModel>> searchFlights(SearchRequestModel request) {
        Map<String, String> queryMap = buildQueryMap(request);
        return flightService.getFlights(queryMap)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(RawFlightDetailsMapper::apply);
    }

    private Map<String, String> buildQueryMap(SearchRequestModel request) {
        Map<String, String> queryMap = new HashMap<>();

        queryMap.put(FlightService.SearchFlightsParams.ORIGIN, request.getOrigin());
        queryMap.put(FlightService.SearchFlightsParams.DESTINATION, request.getDestination());
        if (request.getAdults() > 0)
            queryMap.put(FlightService.SearchFlightsParams.ADULTS, String.valueOf(request.getAdults()));
        if (request.getTeens() > 0)
            queryMap.put(FlightService.SearchFlightsParams.TEENS, String.valueOf(request.getTeens()));
        if (request.getChildren() > 0)
            queryMap.put(FlightService.SearchFlightsParams.CHILDREN, String.valueOf(request.getChildren()));
        queryMap.put(FlightService.SearchFlightsParams.DATEOUT, departureRequestFormat.format(request.getDeparture()));

        return queryMap;
    }

}