package com.mharbovskyi.searchflightstask.view;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mharbovskyi.searchflightstask.R;
import com.mharbovskyi.searchflightstask.model.Station;
import com.mharbovskyi.searchflightstask.presenter.SearchStationContract;
import com.mharbovskyi.searchflightstask.presenter.SearchStationPresenter;

import java.util.List;
import java.util.function.ToDoubleBiFunction;

public class SearchStationFragment extends AbstractFragment implements SearchStationContract.View {

    private SearchStationContract.Presenter presenter;
    private OnFragmentInteractionListener interactionListener;

    private Button searchButton;
    private EditText searchText;
    private RecyclerView stationRecyclerView;

    public SearchStationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        presenter = new SearchStationPresenter(this);

        View view = inflater.inflate(R.layout.fragment_search_station, container, false);
        searchButton = view.findViewById(R.id.search_station_button);
        searchText = view.findViewById(R.id.search_station_text);
        stationRecyclerView = view.findViewById(R.id.station_list);

        searchButton.setOnClickListener(v->presenter.searchButtonClicked());

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            interactionListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.destroy();
        presenter = null;
        interactionListener = null;
    }

    @Override
    public void loadStations(List<Station> stations) {

    }

    @Override
    public String getStationSearchText() {
        return searchText.getText().toString();
    }

    @Override
    public void goToSearchScreen(Station station) {
        interactionListener.onFragmentInteraction(station);
    }

    @Override
    protected ViewGroup getRootLayout() {
        // TODO: 17.07.18  
        return null;
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
        void onFragmentInteraction(Station station);
    }
}
