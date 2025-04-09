package com.heydar.simplemvp.data.network;

import com.heydar.simplemvp.model.request.LoginRequest;
import com.heydar.simplemvp.model.response.LoginResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("login")
    Single<LoginResponse> login(@Body LoginRequest request);
}
