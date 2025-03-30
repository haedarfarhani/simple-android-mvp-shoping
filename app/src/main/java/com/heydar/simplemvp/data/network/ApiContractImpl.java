package com.heydar.simplemvp.data.network;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.heydar.simplemvp.model.BaseResult;
import com.heydar.simplemvp.model.Result;
import com.heydar.simplemvp.model.response.LoginResponse;
import com.heydar.simplemvp.model.response.UserInfoResponse;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ApiContractImpl implements ApiContract {
    private final OkHttpHelper httpHelper = new OkHttpHelper();
    private static final Map<String, String> HEADERS = Map.of("Content-Type", "application/json");
    private final Gson gson = new Gson();

    @NonNull
    @Override
    public Single<BaseResult<LoginResponse>> getLogin(@NonNull HashMap<String, Object> params) {
        String url = ApiEndPoint.BASE_URL + "login";
        return httpHelper.post(url, params, HEADERS)
                .subscribeOn(Schedulers.io())
                .map(response -> parseResponse(response, new TypeToken<BaseResult<LoginResponse>>() {}))
                .onErrorReturn(error -> new BaseResult<>(new Result<>(false, error.getMessage(), null, 0)));
    }

    @NonNull
    @Override
    public Completable getLogout() {
        String url = ApiEndPoint.BASE_URL + "logout";
        return httpHelper.post(url, new HashMap<>(), HEADERS)
                .subscribeOn(Schedulers.io())
                .ignoreElement();
    }

    @NonNull
    @Override
    public Single<BaseResult<LoginResponse>> getSignup(@NonNull HashMap<String, Object> params) {
        String url = ApiEndPoint.BASE_URL + "signup";
        return httpHelper.post(url, params, HEADERS)
                .subscribeOn(Schedulers.io())
                .map(response -> parseResponse(response, new TypeToken<BaseResult<LoginResponse>>() {}))
                .onErrorReturn(error -> new BaseResult<>(new Result<>(false, error.getMessage(), null, 0)));
    }

    @NonNull
    @Override
    public Single<BaseResult<UserInfoResponse>> getUserInfo() {
        String url = ApiEndPoint.BASE_URL + "user/info";
        return httpHelper.get(url, HEADERS)
                .subscribeOn(Schedulers.io())
                .map(response -> parseResponse(response, new TypeToken<BaseResult<UserInfoResponse>>() {}))
                .onErrorReturn(error -> new BaseResult<>(new Result<>(false, error.getMessage(), null, 0)));
    }

    private <T> T parseResponse(String response, TypeToken<T> typeToken) {
        Type type = typeToken.getType();
        return gson.fromJson(response, type);
    }
}
