package com.mharbovskyi.searchflightstask.presentetion.contracts;

import android.support.annotation.StringRes;

public interface BaseContract {

    /**
     * this decoupling is done for case
     * when we have abstract fragment which can have
     * default implementations of this method
     * but do not want to take part in generic bounding stuff
     * for injecting presenters
     */
    interface WarningView {
        void showError(@StringRes int messageResourceId);
        void showLoading(@StringRes int messageResourceId);
        void hideLoading();
    }

    interface View<P extends Presenter<? extends View<P>>> extends WarningView {
        void setPresenter(P presenter);
    }
    
    interface Presenter<V extends View<? extends Presenter<V>>> {
        void destroy();
        void setView(V view);
    }
}
