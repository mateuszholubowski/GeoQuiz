package com.codete.geoquiz;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


public class SplashActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (savedInstanceState == null) {
            SplashFragment fragment = new SplashFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    fragment, SplashFragment.TAG).commit();
        }
    }
}
