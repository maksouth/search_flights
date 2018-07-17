package com.mharbovskyi.searchflightstask.datasource.network;

import com.mharbovskyi.searchflightstask.datasource.network.rawmodel.mapper.RawStationMapper;
import com.mharbovskyi.searchflightstask.datasource.network.service.StationListService;
import com.mharbovskyi.searchflightstask.model.Station;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class StationsDataSource {

    private final Retrofit retrofit;
    private StationListService service;
    private Observable<List<Station>> stationListObservable;

    public StationsDataSource() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(StationListService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

        service = retrofit.create(StationListService.class);
        stationListObservable = service.getStations()
                .subscribeOn(Schedulers.io())
                .flatMap(stationList -> Observable.fromArray(stationList.stations))
                .map(RawStationMapper::apply)
                .toList()
                .toObservable()
                .cache();
    }

    public Observable<List<Station>> getStations() {
        return stationListObservable;
    }
}
