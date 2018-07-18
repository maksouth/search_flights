package com.mharbovskyi.searchflightstask.view;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mharbovskyi.searchflightstask.R;
import com.mharbovskyi.searchflightstask.model.FlightDetailsModel;
import com.mharbovskyi.searchflightstask.presentetion.contracts.FlightListContract;
import com.mharbovskyi.searchflightstask.presentetion.presenters.FlightListPresenter;
import com.mharbovskyi.searchflightstask.view.adapters.FlightListAdapter;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class FlightListFragment extends AbstractFragment implements FlightListContract.View {

    public static final String ARGUMENT_FLIGHTS_LIST = "flights-list";

    private NavigationListeners.ShowFlightDetailsNavigationListener flightDetailsNavigationListener;
    private FlightListPresenter presenter;

    private FlightListAdapter adapter;

    private Disposable recyclerViewItemDisposable;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    public FlightListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flight_list, container, false);
        recyclerView = view.findViewById(R.id.flights_list);
        adapter = new FlightListAdapter();
        presenter = new FlightListPresenter(this);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        if(getArguments() != null) {
            List<FlightDetailsModel> flights = getArguments().getParcelableArrayList(ARGUMENT_FLIGHTS_LIST);
            presenter.loadFlights(flights);
        }

        recyclerViewItemDisposable = adapter.getPositionClick()
                .subscribe(presenter::flightClicked);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof NavigationListeners.ShowFlightDetailsNavigationListener) {
            flightDetailsNavigationListener = (NavigationListeners.ShowFlightDetailsNavigationListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement NavigationListeners.ShowFlightDetailsNavigationListener");
        }
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
        adapter.loadFlights(flights);
    }

    @Override
    public void goToFlightDetailsScreen(FlightDetailsModel flightDetailsModel) {
        flightDetailsNavigationListener.goToFlightDetailsScreen(flightDetailsModel);
    }
}
