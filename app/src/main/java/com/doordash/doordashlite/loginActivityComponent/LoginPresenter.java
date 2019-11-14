package com.doordash.doordashlite.loginActivityComponent;

import com.doordash.doordashlite.data.Repository;
import com.doordash.doordashlite.data.TokenResponse;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by tarun on 11/9/18.
 */

public class LoginPresenter implements LoginContract.Presenter {

    Repository repository;
    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view, Repository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void submitCredentials(String username, String password) {
        Observable<TokenResponse> observable = repository.getTokenData(username, password);
        observable.subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<TokenResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                view.showError();
            }

            @Override
            public void onNext(TokenResponse tokenResponse) {
                view.storeTokenData(tokenResponse.getToken());
            }
        });
    }

    @Override
    public void checkTokenExists() {

    }
}
