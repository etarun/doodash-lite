package com.doordash.doordashlite.loginActivityComponent;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.doordash.doordashlite.BaseActivity;
import com.doordash.doordashlite.R;
import com.doordash.doordashlite.mainActivityComponent.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tarun on 11/9/18.
 */

public class LoginActivity extends BaseActivity implements LoginContract.View {

    @BindView(R.id.username)
    EditText username;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.button_submit)
    Button btnSubmit;

    @Inject
    LoginContract.Presenter presenter;
    private SharedPreferences sharedPreferences;

    private LoginActivityComponent loginActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initViews();

        initLoginComponent();

        presenter.checkTokenExists();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.submitCredentials(username.getText().toString(), password.getText().toString());
            }
        });

    }

    private void initLoginComponent() {
        super.initDaggerComponent();
        if (loginActivityComponent == null) {
            loginActivityComponent = DaggerLoginActivityComponent.builder()
                    .appComponent(appComponent)
                    .loginActivityModule(new LoginActivityModule(this))
                    .build();

            loginActivityComponent.inject(this);
        }
    }

    @Override
    public void storeTokenData(String token) {
        sharedPreferences = this.getSharedPreferences("doordashLite", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", token);
        editor.apply();

        startActivity(new Intent(this, MainActivity.class));
    }

    private void initViews() {

    }

    @Override
    public void showError() {

    }
}
