package com.heydar.simplemvp.presenter.base;

import com.heydar.simplemvp.data.network.ApiService;
import com.heydar.simplemvp.utils.SchedulerProvider;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private static final String TAG = "BasePresenter";
    private final CompositeDisposable compositeDisposable;
    protected final SchedulerProvider schedulerProvider;

    private V mMvpView;

    public BasePresenter(CompositeDisposable compositeDisposable, SchedulerProvider schedulerProvider) {
        this.compositeDisposable = compositeDisposable;
        this.schedulerProvider = schedulerProvider;
    }


    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void onDetach() {
        compositeDisposable.dispose();
        mMvpView = null;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public V getMvpView() {
        return mMvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    @Override
    public void handleApiError(String error) {
        if (error == null) {
            getMvpView().onError("");
            return;
        }
    }

    @Override
    public void setUserAsLoggedOut() {

    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.onAttach(MvpView) before" + " requesting data to the Presenter");
        }
    }
}
