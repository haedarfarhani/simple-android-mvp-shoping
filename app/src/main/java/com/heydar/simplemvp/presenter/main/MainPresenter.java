package com.heydar.simplemvp.presenter.main;

import com.heydar.simplemvp.data.network.ApiService;
import com.heydar.simplemvp.presenter.base.BasePresenter;
import com.heydar.simplemvp.utils.SchedulerProvider;
import com.heydar.simplemvp.view.login.LoginMvpView;
import com.heydar.simplemvp.view.main.MainMvpView;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V> {


    public MainPresenter(ApiService apiService, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(apiService, schedulerProvider, compositeDisposable);
    }
}
