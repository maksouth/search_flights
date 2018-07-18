package com.mharbovskyi.searchflightstask.view;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
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
import com.mharbovskyi.searchflightstask.presenter.SearchFlightContract;
import com.mharbovskyi.searchflightstask.presenter.SearchFlightPresenter;

import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SearchFlightFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class SearchFlightFragment extends AbstractFragment
        implements SearchFlightContract.View {

    private SearchFlightContract.Presenter presenter;

    private OnFragmentInteractionListener mListener;

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

        presenter = new SearchFlightPresenter(this, ((MainActivity)getActivity()).getFlightDataSource());

        searchButton.setOnClickListener(v -> presenter.searchButtonClicked());

        adultsNumberSeekBar.setOnSeekBarChangeListener((AbstractSeekBarListener) (seekBar, progress, fromUser)
                -> adultsNumberLabel.setText(String.valueOf(progress)));
        teensNumberSeekBar.setOnSeekBarChangeListener((AbstractSeekBarListener) (seekBar, progress, fromUser)
                -> teensNumberLabel.setText(String.valueOf(progress)));
        childrenNumberSeekBar.setOnSeekBarChangeListener((AbstractSeekBarListener) (seekBar, progress, fromUser)
                -> childrenNumberLabel.setText(String.valueOf(progress)));

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        presenter.destroy();
        presenter = null;
    }

    public void onNewOrigin(Station station) {
        presenter.onNewOrigin(station);
    }

    public void onNewDestination(Station station) {
        presenter.onNewDestination(station);
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
    public void goToFlightResultScreen(List<FlightDetailsModel> flights) {
        mListener.onSearchFlightInteraction(flights);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onSearchFlightInteraction(List<FlightDetailsModel> flights);
    }

    @FunctionalInterface
    private interface AbstractSeekBarListener extends SeekBar.OnSeekBarChangeListener {
        default void onStartTrackingTouch(SeekBar seekBar) {
            //intentionally left empty
        }

        default void onStopTrackingTouch(SeekBar seekBar) {
            //intentionally left empty
        }
    }
}
