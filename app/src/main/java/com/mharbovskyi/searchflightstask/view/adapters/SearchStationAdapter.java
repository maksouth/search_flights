package com.mharbovskyi.searchflightstask.view.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mharbovskyi.searchflightstask.R;
import com.mharbovskyi.searchflightstask.model.Station;

import java.util.ArrayList;
import java.util.List;

public class SearchStationAdapter extends RecyclerView.Adapter<SearchStationAdapter.ViewHolder> {

    private List<Station> stationList = new ArrayList<>();

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView stationName;
        TextView stationCode;

        ViewHolder(View view) {
            super(view);
            stationName = view.findViewById(R.id.station_name_label);
            stationCode = view.findViewById(R.id.station_code_label);
        }

        void populate(Station station) {
            stationName.setText(station.getCity());
            stationCode.setText(station.getCode());
        }
    }

    public void loadStations(List<Station> newStationList) {
        stationList = newStationList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_station, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.populate(stationList.get(position));
    }

    @Override
    public int getItemCount() {
        return stationList.size();
    }

}
