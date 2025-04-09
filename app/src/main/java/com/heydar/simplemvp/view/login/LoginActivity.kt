package com.heydar.simplemvp.view.login

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.heydar.simplemvp.R
import com.heydar.simplemvp.base.BaseActivity
import com.heydar.simplemvp.presenter.login.LoginMvpPresenter
import com.heydar.simplemvp.utils.AppLogger
import javax.inject.Inject


class LoginActivity : BaseActivity(), LoginMvpView {
    @Inject
    lateinit var mPresenter: LoginMvpPresenter<LoginMvpView>
    private lateinit var loginBtn: Button
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        activityComponent.inject(this)
        mPresenter.onAttach(this)
        loginBtn = findViewById(R.id.login_button)
        emailEditText = findViewById(R.id.email_edit_text)
        passwordEditText = findViewById(R.id.password_edit_text)

        loginBtn.setOnClickListener { v ->
            val email: String = emailEditText.getText().toString().trim()
            val password: String = passwordEditText.getText().toString().trim()
            mPresenter.onLoginClick(email, password)
        }
    }

    override fun showLoading() {
        super.showLoading()
    }

    override fun hideLoading() {
        super.hideLoading()
    }

    override fun onDestroy() {
        mPresenter.onDetach()
        super.onDestroy()
    }

    override fun onLoginSuccess(token: String?) {
        AppLogger.d("test", token)
    }
}