package com.mharbovskyi.searchflightstask.view;


import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.mharbovskyi.searchflightstask.R;
import com.mharbovskyi.searchflightstask.presenter.BaseContract;

public abstract class AbstractFragment extends Fragment implements BaseContract.View {

    private ProgressBar progressBar;

    public AbstractFragment() {
        // Required empty public constructor
    }

    @Override
    public void showError(int messageResourceId) {
        Snackbar.make(getRootLayout(), getString(messageResourceId), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showLoading(int messageResourceId) {

    }

    @Override
    public void hideLoading() {

    }

    protected ViewGroup getRootLayout(){
        return getActivity().findViewById(R.id.fragment_container);
    };
}
