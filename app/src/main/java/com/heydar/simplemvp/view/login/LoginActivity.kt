package com.heydar.simplemvp.view.login

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.heydar.simplemvp.R
import com.heydar.simplemvp.base.BaseActivity
import com.heydar.simplemvp.di.component.NetworkComponent
import com.heydar.simplemvp.di.module.ApplicationModule
import com.heydar.simplemvp.presenter.login.LoginMvpPresenter
import javax.inject.Inject


class LoginActivity : BaseActivity(), LoginMvpView  {

    @Inject
    lateinit var mPresenter: LoginMvpPresenter<LoginMvpView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        DaggerNetworkComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
            .inject(this)
        activityComponent.inject(this)
        mPresenter.onAttach(this)
    }

    override fun onLoginSuccess(token: String?) {

    }
}