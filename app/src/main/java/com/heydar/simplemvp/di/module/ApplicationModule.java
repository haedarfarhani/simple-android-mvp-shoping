package com.heydar.simplemvp.di.module;

import android.app.Application;
import android.content.Context;

import com.heydar.simplemvp.data.local.database.ObjectBox;
import com.heydar.simplemvp.di.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    @ApplicationContext
    Context provideContext() {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    ObjectBox provideObjectBox(@ApplicationContext Context context) {
        return new ObjectBox(context);
    }
}