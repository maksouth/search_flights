package com.mharbovskyi.searchflightstask.di.component;

import android.content.Context;

import com.mharbovskyi.searchflightstask.di.module.FragmentsProviderModule;
import com.mharbovskyi.searchflightstask.di.module.MockDataSourceModule;
import com.mharbovskyi.searchflightstask.view.MainActivity;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {MockDataSourceModule.class, FragmentsProviderModule.class})
public interface ActivityComponent {

    void inject(MainActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder context(Context context);
        ActivityComponent build();
    }

}