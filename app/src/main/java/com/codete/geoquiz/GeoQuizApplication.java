package com.codete.geoquiz;

import android.app.Application;

import com.codete.geoquiz.webservice.ServiceTaskRunner;

/**
 * Created by Mateusz on 2015-08-08.
 */
public class GeoQuizApplication extends Application {


    private static ServiceTaskRunner serviceTaskRunner;

    public static ServiceTaskRunner getServiceTaskRunner() {
        return serviceTaskRunner;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        serviceTaskRunner = new ServiceTaskRunner();
    }
}
