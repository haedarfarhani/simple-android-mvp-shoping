package com.heydar.simplemvp.di.component;

import com.heydar.simplemvp.di.module.ActivityModule;
import com.heydar.simplemvp.view.main.MainActivity;
import com.heydar.simplemvp.di.PerActivity;
import com.heydar.simplemvp.view.login.LoginActivity;
import com.heydar.simplemvp.view.productadd.ProductAddActivity;
import com.heydar.simplemvp.view.productlist.ProductListActivity;
import com.heydar.simplemvp.view.signup.SignupActivity;

import dagger.Component;


@PerActivity
@Component(dependencies = {ApplicationComponent.class, NetworkComponent.class}, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);

    void inject(LoginActivity activity);

    void inject(ProductAddActivity activity);

    void inject(ProductListActivity activity);

    void inject(SignupActivity fragment);

}
