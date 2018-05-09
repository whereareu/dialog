package com.dialogapp.dialog.ui.loginscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;

import com.dialogapp.dialog.R;
import com.dialogapp.dialog.api.ServiceInterceptor;
import com.dialogapp.dialog.ui.MainActivity;
import com.dialogapp.dialog.ui.base.BaseInjectableActivity;
import com.orhanobut.hawk.Hawk;

import javax.inject.Inject;

public class LoginActivity extends BaseInjectableActivity implements LoginFragment.LoginFragmentEventListener {
    @Inject
    ServiceInterceptor serviceInterceptor;

    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank);
        coordinatorLayout = findViewById(R.id.coord_layout_blank);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_blank, new LoginFragment())
                    .commit();
        }
    }

    @Override
    public void onVerificationFailed() {
        Snackbar.make(coordinatorLayout, R.string.login_verification_failed, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onInvalidToken() {
        Snackbar.make(coordinatorLayout, R.string.login_invalid_token, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onVerificationComplete(String token, String username, String fullName, String avatarUrl) {
        serviceInterceptor.setAuthToken(token);

        Hawk.put(getString(R.string.pref_token), token);
        Hawk.put(getString(R.string.pref_username), username);
        Hawk.put(getString(R.string.pref_fullname), fullName);
        Hawk.put(getString(R.string.pref_avatar_url), avatarUrl);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
