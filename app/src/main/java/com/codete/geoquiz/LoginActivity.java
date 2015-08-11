package com.codete.geoquiz;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


public class LoginActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(savedInstanceState == null){
            LoginFragment fragment = new LoginFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment, LoginFragment.TAG).commit();
        }

    }
}
