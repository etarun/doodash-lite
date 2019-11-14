package com.doordash.doordashlite.loginActivityComponent;

/**
 * Created by tarun on 11/9/18.
 */

public class LoginContract {

    public interface View {
        void storeTokenData(String token);

        void showError();
    }

    public interface Presenter {
        void submitCredentials(String username, String password);

        void checkTokenExists();
    }
}
