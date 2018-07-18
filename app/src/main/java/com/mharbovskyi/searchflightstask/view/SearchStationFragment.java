package com.mharbovskyi.searchflightstask.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mharbovskyi.searchflightstask.R;
import com.mharbovskyi.searchflightstask.model.Station;
import com.mharbovskyi.searchflightstask.presentetion.contracts.SearchStationContract;
import com.mharbovskyi.searchflightstask.presentetion.presenters.SearchStationPresenter;
import com.mharbovskyi.searchflightstask.view.adapters.SearchStationAdapter;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class SearchStationFragment extends AbstractFragment implements SearchStationContract.View {

    private SearchStationContract.Presenter presenter;
    private OnNewStationListener interactionListener;

    private SearchStationAdapter adapter;

    private Disposable recyclerViewItemDisposable;

    private Button searchButton;
    private EditText searchText;
    private RecyclerView stationRecyclerView;
    private RecyclerView.LayoutManager layoutManager;

    public SearchStationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_station, container, false);
        searchButton = view.findViewById(R.id.search_station_button);
        searchText = view.findViewById(R.id.search_station_text);
        stationRecyclerView = view.findViewById(R.id.station_list);

        stationRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        stationRecyclerView.setLayoutManager(layoutManager);
        adapter = new SearchStationAdapter();
        stationRecyclerView.setAdapter(adapter);

        presenter = new SearchStationPresenter(this,
                ((MainActivity)getActivity()).getStationsDataSource());
        searchButton.setOnClickListener(v->presenter.searchButtonClicked());
        presenter.start();

        recyclerViewItemDisposable = adapter.getPositionClick()
                .subscribe(presenter::stationCLicked);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnNewStationListener) {
            interactionListener = (OnNewStationListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ShowFlightListNavigationListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (!recyclerViewItemDisposable.isDisposed())
            recyclerViewItemDisposable.dispose();
        presenter.destroy();
        presenter = null;
        interactionListener = null;
    }

    @Override
    public void loadStations(List<Station> stations) {
        adapter.loadStations(stations);
    }

    @Override
    public String getStationSearchText() {
        return searchText.getText().toString();
    }

    @Override
    public void goToSearchScreen(Station station) {
        interactionListener.onNewStation(station);
    }

    public interface OnNewStationListener {
        void onNewStation(Station station);
    }
}
