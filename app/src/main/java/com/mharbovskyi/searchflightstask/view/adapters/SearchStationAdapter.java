package com.mharbovskyi.searchflightstask.view.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mharbovskyi.searchflightstask.R;
import com.mharbovskyi.searchflightstask.model.Station;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class SearchStationAdapter extends RecyclerView.Adapter<SearchStationAdapter.ViewHolder> {

    private static final String TAG = SearchStationAdapter.class.getSimpleName();

    private List<Station> stationList = new ArrayList<>();
    private final PublishSubject<Station> onClickStationSubject = PublishSubject.create();

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView stationName;
        TextView stationCode;
        // TODO: 17.07.18 is it ok?
        Station value;

        ViewHolder(View view) {
            super(view);
            stationName = view.findViewById(R.id.station_name_label);
            stationCode = view.findViewById(R.id.station_code_label);
        }

        void populate(Station station) {
            value = station;
            stationName.setText(station.getCity());
            stationCode.setText(station.getCode());
        }
    }

    public void loadStations(List<Station> newStationList) {
        stationList = newStationList;
        notifyDataSetChanged();
    }

    public Observable<Station> getPositionClick() {
        return onClickStationSubject;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_station, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.populate(stationList.get(position));
        holder.itemView.setOnClickListener(v->{
            Log.d(TAG, "Station clicked " + holder.value);
            onClickStationSubject.onNext(holder.value);
            onClickStationSubject.onComplete();
        });
    }

    @Override
    public int getItemCount() {
        return stationList.size();
    }

}
