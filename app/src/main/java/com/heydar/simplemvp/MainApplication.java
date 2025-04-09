package com.heydar.simplemvp;

import android.app.Application;

import com.heydar.simplemvp.data.local.database.ObjectBox;
import com.heydar.simplemvp.di.component.ApplicationComponent;
import com.heydar.simplemvp.di.component.DaggerApplicationComponent;
import com.heydar.simplemvp.di.component.DaggerNetworkComponent;
import com.heydar.simplemvp.di.component.NetworkComponent;
import com.heydar.simplemvp.di.module.ApplicationModule;

import javax.inject.Inject;

public class MainApplication extends Application {
    @Inject
    ObjectBox mDataManager;

    private ApplicationComponent mApplicationComponent;
    private NetworkComponent networkComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();

        networkComponent = DaggerNetworkComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        mApplicationComponent.inject(this);

    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    public NetworkComponent getNetworkComponent() {
        return networkComponent;
    }

    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
