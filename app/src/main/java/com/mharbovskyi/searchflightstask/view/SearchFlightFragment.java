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
import com.mharbovskyi.searchflightstask.presenter.SearchFlightContract;

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
    }

    @Override
    public String getOrigin() {
        return null;
    }

    @Override
    public String getDestination() {
        return null;
    }

    @Override
    public void setOrigin(String text) {

    }

    @Override
    public void setDestination(String text) {

    }

    @Override
    public Date getDeparture() {
        return null;
    }

    @Override
    public String getNumberOfAdults() {
        return null;
    }

    @Override
    public String getNumberOfTeens() {
        return null;
    }

    @Override
    public String getNumberOfChildren() {
        return null;
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
}
