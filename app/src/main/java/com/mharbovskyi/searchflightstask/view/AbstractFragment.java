package com.mharbovskyi.searchflightstask.view;


import android.app.ProgressDialog;
import android.widget.Toast;

import com.mharbovskyi.searchflightstask.presentation.contracts.BaseContract;

import dagger.android.DaggerFragment;

public abstract class AbstractFragment extends DaggerFragment implements BaseContract.View {

    private ProgressDialog progressDialog;

    public AbstractFragment() {
        // Required empty public constructor
    }

    @Override
    public void showError(int messageResourceId) {
        Toast.makeText(getActivity(), getString(messageResourceId), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading(int messageResourceId) {
        if(progressDialog != null){
            progressDialog.dismiss();
        } else {
            progressDialog = new ProgressDialog(getActivity());
        }

        progressDialog.setMessage(getString(messageResourceId));
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        if(progressDialog!=null){
            progressDialog.dismiss();
        }

        progressDialog = null;
    }
}
