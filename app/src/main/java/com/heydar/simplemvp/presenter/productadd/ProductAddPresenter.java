package com.heydar.simplemvp.presenter.productadd;

import com.heydar.simplemvp.data.network.ApiService;
import com.heydar.simplemvp.presenter.base.BasePresenter;
import com.heydar.simplemvp.utils.SchedulerProvider;
import com.heydar.simplemvp.view.productadd.ProductAddMvpView;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class ProductAddPresenter<V extends ProductAddMvpView> extends BasePresenter<V> implements ProductAddMvpPresenter<V> {

    public ProductAddPresenter(ApiService apiService, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(apiService, schedulerProvider, compositeDisposable);
    }
}
