package com.heydar.simplemvp.di.module;

import android.content.Context;

import com.heydar.simplemvp.BuildConfig;
import com.heydar.simplemvp.data.network.ApiService;
import com.heydar.simplemvp.di.ApplicationContext;
import com.heydar.simplemvp.utils.AppConstants;

import java.io.File;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    HttpUrl provideBaseUrl() {
        return HttpUrl.parse(AppConstants.API_ENDPOINT); // فرضاً API_ENDPOINT توی AppConstants تعریف شده
    }

    @Provides
    @Singleton
    Cache provideCache(@ApplicationContext Context context) {
        File cacheDir = new File(context.getCacheDir(), "okhttp_cache");
        long maxSize = 20L * 1024 * 1024; // 20 MB
        return new Cache(cacheDir, maxSize);
    }

    @Provides
    @Singleton
    Interceptor provideHeaderInterceptor() {
        return chain -> {
            Request original = chain.request();
            Request.Builder builder = original.newBuilder()
                    .header("Authorization", "Bearer YOUR_TOKEN_HERE")
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json");
            Request request = builder.build();
            return chain.proceed(request);
        };
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache, Interceptor headerInterceptor) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        return new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(headerInterceptor)
                .addInterceptor(logging)
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    @Singleton
    Executor provideCallbackExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Provides
    @Singleton
    Converter.Factory provideConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    CallAdapter.Factory provideCallAdapterFactory() {
        return RxJava3CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    ApiService provideUserApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient client, HttpUrl baseUrl, Executor callbackExecutor, Converter.Factory converterFactory, CallAdapter.Factory callAdapterFactory) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .callbackExecutor(callbackExecutor)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .validateEagerly(BuildConfig.DEBUG)
                .build();
    }
}
