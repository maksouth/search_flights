package com.mharbovskyi.searchflightstask.datasource.network;

import com.mharbovskyi.searchflightstask.datasource.network.service.FlightService;
import com.mharbovskyi.searchflightstask.model.FlightDetailsModel;
import com.mharbovskyi.searchflightstask.model.SearchRequestModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class FlightNetworkDataSource {

    private final Retrofit retrofit;
    private final FlightService flightService;

    public FlightNetworkDataSource() {
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

    Observable<List<FlightDetailsModel>> searchFlights(SearchRequestModel request) {
        Map<String, String> queryMap = new HashMap<>();
        // TODO: 16.07.18 populate query map and send request
        // TODO: 16.07.18 map results to model
        return Observable.empty();
    }

}
