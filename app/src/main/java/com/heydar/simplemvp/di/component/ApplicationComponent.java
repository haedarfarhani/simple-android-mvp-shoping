package com.heydar.simplemvp.di.component;

import android.app.Application;
import android.content.Context;


import com.heydar.simplemvp.MainApplication;
import com.heydar.simplemvp.data.local.database.ObjectBox;
import com.heydar.simplemvp.di.ApplicationContext;
import com.heydar.simplemvp.di.module.ApplicationModule;
import com.heydar.simplemvp.di.module.NetworkModule;
import com.heydar.simplemvp.service.SyncService;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(MainApplication app);

    void inject(SyncService service);

    @ApplicationContext
    Context context();

    Application application();

    ObjectBox getDataManager();
}
