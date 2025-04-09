package com.heydar.simplemvp.repository;

import com.heydar.simplemvp.data.network.ApiService;
import com.heydar.simplemvp.model.request.LoginRequest;
import com.heydar.simplemvp.model.response.LoginResponse;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class LoginRepository {
    private final ApiService apiService;

    @Inject
    public LoginRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public Single<LoginResponse> performLogin(String username, String password) {
        return apiService.login(new LoginRequest(username, password));
    }
}
