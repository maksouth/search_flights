package com.mharbovskyi.searchflightstask.presentation.contracts;

import android.support.annotation.StringRes;

public interface BaseContract {

    interface View {
        void showError(@StringRes int messageResourceId);
        void showLoading(@StringRes int messageResourceId);
        void hideLoading();
    }
    
    interface Presenter {
        void destroy();
    }
}
