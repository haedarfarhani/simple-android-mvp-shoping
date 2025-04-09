package com.heydar.simplemvp.di.module;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.heydar.simplemvp.data.network.ApiService;
import com.heydar.simplemvp.di.ActivityContext;
import com.heydar.simplemvp.di.PerActivity;
import com.heydar.simplemvp.presenter.login.LoginMvpPresenter;
import com.heydar.simplemvp.presenter.login.LoginPresenter;
import com.heydar.simplemvp.presenter.main.MainMvpPresenter;
import com.heydar.simplemvp.presenter.main.MainPresenter;
import com.heydar.simplemvp.presenter.productadd.ProductAddMvpPresenter;
import com.heydar.simplemvp.presenter.productadd.ProductAddPresenter;
import com.heydar.simplemvp.repository.LoginRepository;
import com.heydar.simplemvp.utils.AppSchedulerProvider;
import com.heydar.simplemvp.utils.SchedulerProvider;
import com.heydar.simplemvp.view.login.LoginMvpView;
import com.heydar.simplemvp.view.main.MainMvpView;
import com.heydar.simplemvp.view.productadd.ProductAddMvpView;

import dagger.Module;
import dagger.Provides;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

@Module
public class ActivityModule {

    private final AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return activity;
    }

    @Provides
    @PerActivity
    AppCompatActivity provideActivity() {
        return activity;
    }

    @Provides
    @PerActivity
    LinearLayoutManager provideLinearLayoutManager() {
        return new LinearLayoutManager(activity);
    }

    @Provides
    @PerActivity
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @PerActivity
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    LoginRepository provideLoginRepository(ApiService apiService) {
        return new LoginRepository(apiService);
    }

    @Provides
    @PerActivity
    LoginMvpPresenter<LoginMvpView> provideLoginPresenter(CompositeDisposable compositeDisposable, SchedulerProvider schedulerProvider, LoginRepository repository) {
        return new LoginPresenter<>(compositeDisposable, schedulerProvider, repository);
    }

    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> provideMainPresenter(CompositeDisposable compositeDisposable) {
        return new MainPresenter<>(compositeDisposable);
    }

    @Provides
    @PerActivity
    ProductAddMvpPresenter<ProductAddMvpView> provideProductAddPresenter(CompositeDisposable compositeDisposable) {
        return new ProductAddPresenter<>(compositeDisposable);
    }
}