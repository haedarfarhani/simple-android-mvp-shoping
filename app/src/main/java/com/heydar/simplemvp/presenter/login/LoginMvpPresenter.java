package com.heydar.simplemvp.presenter.login;

import com.heydar.simplemvp.presenter.base.MvpPresenter;
import com.heydar.simplemvp.view.login.LoginMvpView;

public interface LoginMvpPresenter<V extends LoginMvpView> extends MvpPresenter<V> {

    void onServerLoginClick(String email, String password);

    void onGoogleLoginClick();

    void onFacebookLoginClick();

}
