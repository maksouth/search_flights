package com.mharbovskyi.searchflightstask.view;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mharbovskyi.searchflightstask.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FlightDetailsFragment extends Fragment {


    public FlightDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_flight_details, container, false);
    }

}