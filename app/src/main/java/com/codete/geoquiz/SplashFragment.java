package com.codete.geoquiz;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class SplashFragment extends Fragment {

    public static final String TAG = "SplashFragment";
    public static final int SPLASH_SCREEN_TIME = 2000;

    private boolean shouldGoNext = false;
    private Handler mSplashHandler;

    public SplashFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mSplashHandler = new Handler();
        mSplashHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (getActivity() != null) {
                    goNext();
                } else
                    shouldGoNext = true;
            }
        }, SPLASH_SCREEN_TIME);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (shouldGoNext)
            goNext();
    }

    private void goNext() {

        if (PreferenceManager.getDefaultSharedPreferences(getActivity()).getBoolean(Preferences
                .PREF_LOGGED, false))
            startActivity(new Intent(getActivity(), MainActivity.class).addFlags(Intent
                    .FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
        else
            startActivity(new Intent(getActivity(), LoginActivity.class).addFlags(Intent
                    .FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));

        getActivity().finish();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View contentView = inflater.inflate(R.layout.fragment_splash, container, false);
        return contentView;
    }


}
