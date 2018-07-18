package com.mharbovskyi.searchflightstask.view;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mharbovskyi.searchflightstask.R;
import com.mharbovskyi.searchflightstask.model.FlightDetailsModel;
import com.mharbovskyi.searchflightstask.presentetion.contracts.FlightListContract;
import com.mharbovskyi.searchflightstask.presentetion.presenters.FlightListPresenter;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class FlightListFragment extends AbstractFragment implements FlightListContract.View {

    private FlightListPresenter presenter;

    private RecyclerView recyclerView;
    private Disposable recyclerViewItemDisposable;

    public FlightListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flight_list, container, false);
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(!recyclerViewItemDisposable.isDisposed())
            recyclerViewItemDisposable.dispose();
        presenter.destroy();
        presenter = null;
    }

    @Override
    public void showFlights(List<FlightDetailsModel> flights) {

    }

    @Override
    public void goToFlightDetailsScreen(FlightDetailsModel flightDetailsModel) {

    }
}
