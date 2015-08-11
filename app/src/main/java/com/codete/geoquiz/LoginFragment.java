package com.codete.geoquiz;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.codete.geoquiz.webservice.ServiceEventHandler;
import com.codete.geoquiz.webservice.task.AuthorizeTask;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {


    public static final String TAG = "LoginFragment";

    public LoginFragment() {
        // Required empty public constructor
    }

    @InjectView(R.id.login_edit)
    EditText loginEdit;
    @InjectView(R.id.password_edit)
    EditText passwordEdit;
    @InjectView(R.id.sign_in_btn)
    Button signInBtn;

    private SharedPreferences pref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View contentView = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.inject(this, contentView);
        signInBtn.setOnClickListener(this);
        pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        loginEdit.setText(pref.getString
                (Preferences.PREF_LOGIN, null));

        return contentView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.sign_in_btn:

                String login = loginEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                if (validateInput(login, password)) {
                    GeoQuizApplication.getServiceTaskRunner().startTask(AuthorizeTask.createTask
                            (login, password, null, loginServiceEventHandler));
                }
                break;
        }
    }

    private static final int MIN_LOGIN_LENGTH = 4;
    private static final int MIN_PASSWORD_LENGTH = 4;

    private boolean validateInput(String login, String password) {

        if (TextUtils.isEmpty(login) || login.length() < MIN_LOGIN_LENGTH) {
            loginEdit.setError(getString(R.string.to_short_login));
            return false;
        }
        if (TextUtils.isEmpty(password) || password.length() < MIN_PASSWORD_LENGTH) {
            passwordEdit.setError(getString(R.string.to_short_password));
            return false;
        }

        return true;
    }

    private ServiceEventHandler loginServiceEventHandler = new ServiceEventHandler() {

        private ProgressDialog dialog;

        @Override
        public void started() {
            if (getActivity() != null)
                dialog = Utilities.createProgressDialog(getActivity(), getActivity().getString(R
                        .string.loading));
        }

        @Override
        public void finishedWithError(String message, int errorCode, Throwable throwable) {
            if (getActivity() != null) {
                if (dialog != null && dialog.isShowing())
                    dialog.dismiss();

                Utilities.createAlertDialog(getActivity(), message);
            }
        }

        @Override
        public void finished() {
            if (getActivity() != null) {
                if (dialog != null && dialog.isShowing())
                    dialog.dismiss();
                pref.edit().putString(Preferences.PREF_LOGIN, loginEdit.getText().toString())
                        .putBoolean(Preferences.PREF_LOGGED, true).commit();
                startActivity(new Intent(getActivity(), MainActivity.class).addFlags(Intent
                        .FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        }
    };
}
