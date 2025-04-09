package com.heydar.simplemvp.presenter.base;

import com.heydar.simplemvp.data.network.ApiService;
import com.heydar.simplemvp.utils.SchedulerProvider;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private static final String TAG = "BasePresenter";
    protected final CompositeDisposable compositeDisposable;
    protected final SchedulerProvider schedulerProvider;
    protected final ApiService apiService;

    private V mvpView;

    public BasePresenter(ApiService apiService, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        this.apiService = apiService;
        this.schedulerProvider = schedulerProvider;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void onAttach(V mvpView) {
        this.mvpView = mvpView;
    }

    @Override
    public void onDetach() {
        compositeDisposable.dispose();
        mvpView = null;
    }

    public boolean isViewAttached() {
        return mvpView != null;
    }

    public V getMvpView() {
        checkViewAttached();
        return mvpView;
    }

    protected void checkViewAttached() {
        if (!isViewAttached()) {
            throw new MvpViewNotAttachedException();
        }
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    @Override
    public void handleApiError(String error) {
        if (isViewAttached()) {
            if (error == null || error.isEmpty()) {
                getMvpView().onError("An unknown error occurred");
            } else {
                getMvpView().onError(error);
            }
        }
    }

    @Override
    public void setUserAsLoggedOut() {
        // منطق خروج کاربر، مثلاً فراخوانی API با apiService
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.onAttach(MvpView) before requesting data to the Presenter");
        }
    }
}