package com.mharbovskyi.searchflightstask.presenter;

public interface BaseContract {
    interface View {
        void showError(int messageResourceId);
        void showLoading(int messageResourceId);
    }

    interface Presenter {
        void destroy();
    }
}
