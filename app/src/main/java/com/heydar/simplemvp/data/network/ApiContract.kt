package com.heydar.simplemvp.data.network

import com.heydar.simplemvp.model.BaseResult
import com.heydar.simplemvp.model.response.LoginResponse
import com.heydar.simplemvp.model.response.UserInfoResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

public interface ApiContract {
    fun getLogin(params: HashMap<String, Any>) : Single<BaseResult<LoginResponse>>
    fun getLogout() : Completable
    fun getSignup(params: HashMap<String, Any>) : Single<BaseResult<LoginResponse>>
    fun getUserInfo() : Single<BaseResult<UserInfoResponse>>
}