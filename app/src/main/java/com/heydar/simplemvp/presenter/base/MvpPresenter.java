package com.heydar.simplemvp.presenter.base;

public interface MvpPresenter<V extends MvpView> {

    void onAttach(V mvpView);

    void onDetach();

    void handleApiError(String error);

    void setUserAsLoggedOut();
}
