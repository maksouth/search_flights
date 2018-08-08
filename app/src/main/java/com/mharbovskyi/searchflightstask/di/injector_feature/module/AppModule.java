package com.mharbovskyi.searchflightstask.di.injector_feature.module;

import com.mharbovskyi.searchflightstask.di.scope.ActivityScope;
import com.mharbovskyi.searchflightstask.view.MainActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.android.AndroidInjectionModule;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Module (includes = AndroidSupportInjectionModule.class)
public abstract class AppModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = {MainActivityModule.class})
    abstract MainActivity bindMainActivity();
}
