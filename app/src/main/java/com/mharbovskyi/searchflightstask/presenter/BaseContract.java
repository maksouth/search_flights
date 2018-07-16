package com.mharbovskyi.searchflightstask.presenter;

public interface BaseContract {
    interface View {
        void showError(int messageResourceId);
        void showLoading(int messageResourceId);
        void hideLoading();
    }

    interface Presenter {
        void destroy();
    }
}
