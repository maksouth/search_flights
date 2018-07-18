package com.mharbovskyi.searchflightstask.view.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mharbovskyi.searchflightstask.R;
import com.mharbovskyi.searchflightstask.model.FlightDetailsModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class FlightListAdapter extends RecyclerView.Adapter<FlightListAdapter.ViewHolder> {

    private static final String TAG = FlightListAdapter.class.getSimpleName();

    private List<FlightDetailsModel> flightList = new ArrayList<>();
    private final PublishSubject<FlightDetailsModel> onCLickFlightSubject = PublishSubject.create();

    public void loadFlights(List<FlightDetailsModel> flights) {
        flightList = flights;
        notifyDataSetChanged();
    }

    public Observable<FlightDetailsModel> getPositionClick() {
        return onCLickFlightSubject;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_flight_summary, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.populate(flightList.get(position));
        holder.itemView.setOnClickListener(v -> {
            Log.d(TAG, "Flight clicked " + holder.value);
            onCLickFlightSubject.onNext(holder.value);
            //no onComplete method, because we can return to this fragment when
            //back button is pressed from flight details screen
        });
    }

    @Override
    public int getItemCount() {
        return flightList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        FlightDetailsModel value;

        TextView dateLabel;
        TextView flightNumberLabel;
        TextView durationLabel;
        TextView regularFarePriceLabel;

        ViewHolder(View view) {
            super(view);
            dateLabel = view.findViewById(R.id.flight_date_label);
            flightNumberLabel = view.findViewById(R.id.flight_number_label);
            durationLabel = view.findViewById(R.id.duration_label);
            regularFarePriceLabel = view.findViewById(R.id.regular_fare_label);
        }

        void populate(FlightDetailsModel flight) {
            value = flight;
            //dateLabel.setText(flight.);
        }

    }

}
