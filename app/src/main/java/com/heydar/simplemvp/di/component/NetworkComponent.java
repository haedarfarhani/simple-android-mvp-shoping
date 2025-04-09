package com.heydar.simplemvp.di.component;

import com.heydar.simplemvp.MainApplication;
import com.heydar.simplemvp.data.network.ApiService;
import com.heydar.simplemvp.di.module.ApplicationModule;
import com.heydar.simplemvp.di.module.NetworkModule;
import com.heydar.simplemvp.view.login.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {NetworkModule.class, ApplicationModule.class})
public interface NetworkComponent {
    Retrofit provideRetrofit();

    void inject(MainApplication app);

    ApiService provideApiService();

    void inject(LoginActivity activity);
}
