package com.heydar.simplemvp.presenter.login;

import com.heydar.simplemvp.data.network.ApiService;
import com.heydar.simplemvp.presenter.base.BasePresenter;
import com.heydar.simplemvp.repository.LoginRepository;
import com.heydar.simplemvp.utils.SchedulerProvider;
import com.heydar.simplemvp.view.login.LoginMvpView;

import javax.inject.Inject;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class LoginPresenter<V extends LoginMvpView> extends BasePresenter<V> implements LoginMvpPresenter<V> {
    private final LoginRepository repository;
    private static final String TAG = "LoginPresenter";
    @Inject
    public LoginPresenter(CompositeDisposable compositeDisposable, SchedulerProvider schedulerProvider, LoginRepository repository) {
        super(compositeDisposable, schedulerProvider);
        this.repository = repository;
    }


    @Override
    public void onLoginClick(String email, String password) {
        getMvpView().showLoading();
        getCompositeDisposable().add(
                repository.performLogin(email, password)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe(
                                response -> {
                                    getMvpView().hideLoading();
                                    getMvpView().onLoginSuccess(response.getSessionToken());
                                },
                                error -> {
                                    getMvpView().hideLoading();
                                    handleApiError(error.getMessage());
                                }
                        )
        );
    }

    @Override
    public void onAttach(V mvpView) {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void handleApiError(String error) {

    }

    @Override
    public void setUserAsLoggedOut() {

    }
}
