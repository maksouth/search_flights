package com.mharbovskyi.searchflightstask.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.mharbovskyi.searchflightstask.R;
import com.mharbovskyi.searchflightstask.model.FlightDetailsModel;
import com.mharbovskyi.searchflightstask.model.Station;
import com.mharbovskyi.searchflightstask.presentation.contracts.SearchFlightContract;
import com.mharbovskyi.searchflightstask.view.AbstractFragment;
import com.mharbovskyi.searchflightstask.view.AbstractSeekBarListener;
import com.mharbovskyi.searchflightstask.view.NavigationListeners;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

// TODO: 08.08.18 bug, origin and destination is placeholder when go back to this fragment, although value is stored
public class SearchFlightFragment extends AbstractFragment
        implements SearchFlightContract.View {

    @Inject
    SearchFlightContract.Presenter presenter;

    private NavigationListeners.ShowFlightListNavigationListener showFlightListFragmentListener;
    private NavigationListeners.ShowSearchStationNavigationListener searchStationFragmentListener;

    private Button searchButton;
    private TextView originLabel;
    private TextView destinationLabel;
    private CalendarView calendarView;
    private SeekBar teensNumberSeekBar;
    private SeekBar adultsNumberSeekBar;
    private SeekBar childrenNumberSeekBar;
    private TextView teensNumberLabel;
    private TextView adultsNumberLabel;
    private TextView childrenNumberLabel;

    public SearchFlightFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_flight, container, false);

        searchButton = view.findViewById(R.id.search_button);
        originLabel = view.findViewById(R.id.origin_label);
        destinationLabel = view.findViewById(R.id.destination_label);
        calendarView = view.findViewById(R.id.calendar_view);
        teensNumberSeekBar = view.findViewById(R.id.teens_seek_bar);
        adultsNumberSeekBar = view.findViewById(R.id.adults_seek_bar);
        childrenNumberSeekBar = view.findViewById(R.id.children_seek_bar);
        teensNumberLabel = view.findViewById(R.id.teens_number_label);
        adultsNumberLabel = view.findViewById(R.id.adults_number_label);
        childrenNumberLabel = view.findViewById(R.id.childen_number_label);

        //presenter.setView(this);

        searchButton.setOnClickListener(v -> presenter.searchButtonClicked());
        originLabel.setOnClickListener(v -> presenter.originLabelClicked());
        destinationLabel.setOnClickListener(v -> presenter.destinationLabelClicked());

        adultsNumberSeekBar.setOnSeekBarChangeListener((AbstractSeekBarListener) (seekBar, progress, fromUser)
                -> adultsNumberLabel.setText(String.valueOf(progress)));
        teensNumberSeekBar.setOnSeekBarChangeListener((AbstractSeekBarListener) (seekBar, progress, fromUser)
                -> teensNumberLabel.setText(String.valueOf(progress)));
        childrenNumberSeekBar.setOnSeekBarChangeListener((AbstractSeekBarListener) (seekBar, progress, fromUser)
                -> childrenNumberLabel.setText(String.valueOf(progress)));

        calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, dayOfMonth);
            view1.setDate(calendar.getTimeInMillis());
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof NavigationListeners.ShowFlightListNavigationListener) {
            showFlightListFragmentListener = (NavigationListeners.ShowFlightListNavigationListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ShowFlightListNavigationListener");
        }

        if (context instanceof NavigationListeners.ShowSearchStationNavigationListener) {
            searchStationFragmentListener = (NavigationListeners.ShowSearchStationNavigationListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ShowSearchStationNavigationListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        showFlightListFragmentListener = null;
        searchStationFragmentListener = null;
        presenter.destroy();
        presenter = null;
    }

    public void onNewStation(Station station) {
        presenter.onNewStation(station);
    }

    @Override
    public void setOrigin(String text) {
        originLabel.setText(text);
    }

    @Override
    public void setDestination(String text) {
        destinationLabel.setText(text);
    }

    @Override
    public Date getDeparture() {
        return new Date(calendarView.getDate());
    }

    @Override
    public int getNumberOfAdults() {
        return adultsNumberSeekBar.getProgress();
    }

    @Override
    public int getNumberOfTeens() {
        return teensNumberSeekBar.getProgress();
    }

    @Override
    public int getNumberOfChildren() {
        return childrenNumberSeekBar.getProgress();
    }

    @Override
    public void goToSearchStationScreen() {
        searchStationFragmentListener.goToSearchStationScreen();
    }

    @Override
    public void goToFlightResultScreen(List<FlightDetailsModel> flights,
                                       Station origin, Station destination) {
        // TODO: 18.07.18 pass origin and destination to set toolbar text
        showFlightListFragmentListener.goToFlightListScreen(flights);
    }
}
