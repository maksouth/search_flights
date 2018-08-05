package com.mharbovskyi.searchflightstask.datasource.network;

import com.mharbovskyi.searchflightstask.datasource.network.rawmodel.mapper.RawStationMapper;
import com.mharbovskyi.searchflightstask.datasource.network.service.StationListService;
import com.mharbovskyi.searchflightstask.model.Station;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class StationsDataSource {

    private Observable<List<Station>> stationListObservable;

    public StationsDataSource(StationListService service) {
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
//
//        retrofit = new Retrofit.Builder()
//                .baseUrl(StationListService.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .client(client)
//                .build();

//        this.service = retrofit.create(StationListService.class);
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
