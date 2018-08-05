package com.mharbovskyi.searchflightstask.view;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mharbovskyi.searchflightstask.R;
import com.mharbovskyi.searchflightstask.presentetion.contracts.FlightDetailsContract;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class FlightDetailsFragment extends AbstractFragment implements FlightDetailsContract.View {

    public static final String ARGUMENT_FLIGHT_DETAILS = "argument-flight-details";

    private TextView originLabel;
    private TextView destinationLabel;
    private TextView infantsLeftLabel;
    private TextView fareClassLabel;
    private TextView discountInPercentLabel;

    FlightDetailsContract.Presenter presenter;

    public FlightDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flight_details, container, false);
        originLabel = view.findViewById(R.id.origin_label);
        destinationLabel = view.findViewById(R.id.destination_label);
        infantsLeftLabel = view.findViewById(R.id.infants_left_label);
        fareClassLabel = view.findViewById(R.id.fare_class_label);
        discountInPercentLabel = view.findViewById(R.id.discount_label);

        presenter.setView(this);
        presenter.loadFlightDetails(getArguments().getParcelable(ARGUMENT_FLIGHT_DETAILS));

        return view;
    }

    @Override
    public void setOrigin(String origin) {
        originLabel.setText(origin);
    }

    @Override
    public void setDestination(String destination) {
        destinationLabel.setText(destination);
    }

    @Override
    public void setInfantsLeft(String infantsLeft) {
        infantsLeftLabel.setText(infantsLeft);
    }

    @Override
    public void setFareClass(String fareClass) {
        fareClassLabel.setText(fareClass);
    }

    @Override
    public void setDiscountInPercent(String discountInPercent) {
        discountInPercentLabel.setText(discountInPercent);
    }

    @Override
    public void setPresenter(FlightDetailsContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
