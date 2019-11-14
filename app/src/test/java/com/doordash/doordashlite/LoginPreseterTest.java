package com.doordash.doordashlite;

import com.doordash.doordashlite.data.Repository;
import com.doordash.doordashlite.data.TokenResponse;
import com.doordash.doordashlite.loginActivityComponent.LoginContract;
import com.doordash.doordashlite.loginActivityComponent.LoginPresenter;
import com.doordash.doordashlite.ui.restaurants.RestaurantsPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import rx.Observable;
import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by tarun on 11/9/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class LoginPreseterTest {

    @Mock
    private LoginContract.View mockView;

    @Mock
    private Repository mockDataRepository;

    @Mock
    private TokenResponse tokenResponse;


    private LoginContract.Presenter presenter;
    @Before
    public void setup() {
        presenter = new LoginPresenter(mockView, mockDataRepository);
        RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.immediate();
            }
        });
    }
    @Test
    public void getToken_test() {
        String username = "username";
        String password = "password";
        when(mockDataRepository.getTokenData(username, password))
                .thenReturn(Observable.error(new Throwable()));
        presenter.submitCredentials(username, password);


        tokenResponse.setToken("testtoken");
        verify(mockView).storeTokenData(tokenResponse.getToken());

        presenter.submitCredentials("user", "pass");
        verify(mockView, never()).storeTokenData(tokenResponse.getToken());

    }
}
