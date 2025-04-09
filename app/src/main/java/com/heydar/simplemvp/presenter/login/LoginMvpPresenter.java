package com.heydar.simplemvp.presenter.login;


import com.heydar.simplemvp.di.PerActivity;
import com.heydar.simplemvp.presenter.base.MvpPresenter;
import com.heydar.simplemvp.view.login.LoginMvpView;

@PerActivity
public interface LoginMvpPresenter<V extends LoginMvpView> extends MvpPresenter<V> {
    void onLoginClick(String email, String password);
}
