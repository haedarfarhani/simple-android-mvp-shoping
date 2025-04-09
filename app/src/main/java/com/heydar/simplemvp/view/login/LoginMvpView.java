package com.heydar.simplemvp.view.login;

import com.heydar.simplemvp.presenter.base.MvpView;

public interface LoginMvpView  extends MvpView {
    void onLoginSuccess(String token);
}
