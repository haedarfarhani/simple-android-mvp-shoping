package com.heydar.simplemvp.presenter.login;

import com.heydar.simplemvp.presenter.base.BasePresenter;
import com.heydar.simplemvp.view.login.LoginMvpView;

public class LoginPresenter<V extends LoginMvpView> extends BasePresenter<V> implements LoginMvpPresenter<V> {

    private static final String TAG = "LoginPresenter";

    @Override
    public void onServerLoginClick(String email, String password) {

    }

    @Override
    public void onGoogleLoginClick() {

    }

    @Override
    public void onFacebookLoginClick() {

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
