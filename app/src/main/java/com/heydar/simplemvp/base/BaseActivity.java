package com.heydar.simplemvp.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.heydar.simplemvp.MainApplication;
import com.heydar.simplemvp.di.component.ActivityComponent;
import com.heydar.simplemvp.di.component.DaggerActivityComponent;
import com.heydar.simplemvp.di.module.ActivityModule;
import com.heydar.simplemvp.di.module.NetworkModule;
import com.heydar.simplemvp.presenter.base.MvpView;
import com.heydar.simplemvp.utils.CommonUtils;

public class BaseActivity extends AppCompatActivity implements MvpView {
    private ProgressDialog mProgressDialog;
    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .networkComponent(((MainApplication) getApplication()).getNetworkComponent())
                .applicationComponent(((MainApplication) getApplication()).getComponent())
                .build();
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void onError(int resId) {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showMessage(int resId) {

    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    @Override
    public void hideKeyboard() {

    }
}
