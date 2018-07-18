package com.mharbovskyi.searchflightstask.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mharbovskyi.searchflightstask.R;
import com.mharbovskyi.searchflightstask.model.FlightDetailsModel;

public class FlightListAdapter {


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
