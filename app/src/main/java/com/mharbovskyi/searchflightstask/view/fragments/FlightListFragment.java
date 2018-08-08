package com.mharbovskyi.searchflightstask.view.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.mharbovskyi.searchflightstask.R;
import com.mharbovskyi.searchflightstask.model.FlightDetailsModel;
import com.mharbovskyi.searchflightstask.presentation.contracts.FlightListContract;
import com.mharbovskyi.searchflightstask.view.AbstractFragment;
import com.mharbovskyi.searchflightstask.view.NavigationListeners;
import com.mharbovskyi.searchflightstask.view.adapters.FlightListAdapter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class FlightListFragment extends AbstractFragment implements FlightListContract.View {

    public static final String ARGUMENT_FLIGHTS_LIST = "flights-list";

    @Inject
    FlightListContract.Presenter presenter;

    private NavigationListeners.ShowFlightDetailsNavigationListener flightDetailsNavigationListener;
    private FlightListAdapter adapter;

    private Disposable recyclerViewItemDisposable;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private SeekBar priceFilterSeekBar;
    private TextView priceFilterValueLabel;
    private TextView priceCurrencyLabel;

    public FlightListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flight_list, container, false);
        recyclerView = view.findViewById(R.id.flights_list);
        priceFilterSeekBar = view.findViewById(R.id.price_filter_seekbar);
        priceFilterValueLabel = view.findViewById(R.id.price_filter_value_label);
        priceCurrencyLabel = view.findViewById(R.id.currency_label);
        adapter = new FlightListAdapter();

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

        priceFilterSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                priceFilterValueLabel.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                presenter.onNewPriceFilter(seekBar.getProgress());
            }
        });

        return view;
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

    @Override
    public void setCurrency(String currency) {
        priceCurrencyLabel.setText(currency);
    }

    @Override
    public void setFilterPrice(int filterPrice) {
        priceFilterValueLabel.setText(String.valueOf(filterPrice));
        priceFilterSeekBar.setProgress(filterPrice);
    }
}
