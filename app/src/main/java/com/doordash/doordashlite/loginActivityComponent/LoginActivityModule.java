package com.doordash.doordashlite.loginActivityComponent;

import com.doordash.doordashlite.data.Repository;
import com.doordash.doordashlite.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tarun on 11/9/18.
 */

@Module
public class LoginActivityModule {

    LoginActivity loginActivity;

    public LoginActivityModule(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
    }

    @Provides
    @ActivityScope
    public LoginContract.View provideRestaurantView() {
        return this.loginActivity;
    }

    @Provides
    @ActivityScope
    public LoginContract.Presenter provideRestaurantPresenter(LoginContract.View view, Repository repository) {
        return new LoginPresenter(view, repository);
    }
}
