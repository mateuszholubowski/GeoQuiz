package com.codete.geoquiz;


import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstScreenFragment extends Fragment implements View.OnClickListener {


    public FirstScreenFragment() {
        // Required empty public constructor
    }

    @InjectView(R.id.logout_btn)
    Button logoutBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View contentView = inflater.inflate(R.layout.fragment_first_screen, container, false);
        ButterKnife.inject(this, contentView);
        logoutBtn.setOnClickListener(this);
        return contentView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logout_btn:
                PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putBoolean
                        (Preferences.PREF_LOGGED, false).commit();
                startActivity(new Intent(getActivity(), SplashActivity.class).addFlags(Intent
                        .FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                break;
        }
    }
}
