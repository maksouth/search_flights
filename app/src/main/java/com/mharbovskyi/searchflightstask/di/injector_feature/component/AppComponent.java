package com.mharbovskyi.searchflightstask.di.injector_feature.component;

import android.content.Context;

import com.mharbovskyi.searchflightstask.MyApplication;
import com.mharbovskyi.searchflightstask.di.injector_feature.module.AppModule;
import com.mharbovskyi.searchflightstask.di.injector_feature.module.SearchFlightModule;
import com.mharbovskyi.searchflightstask.di.module.MockDataSourceModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

// TODO: 08.08.18 SearchFlightModule move to proper component or module
@Singleton
@Component(modules = {MockDataSourceModule.class, AppModule.class, SearchFlightModule.class})
public interface AppComponent {

    void inject(MyApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder context(Context context);
        AppComponent build();
    }

}
