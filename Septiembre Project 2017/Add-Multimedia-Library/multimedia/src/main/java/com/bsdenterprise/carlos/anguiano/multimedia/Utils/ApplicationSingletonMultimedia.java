package com.bsdenterprise.carlos.anguiano.multimedia.Utils;

import android.app.Application;

/**
 * Created by CarlosAnguiano on 11/09/17.
 */

public class ApplicationSingletonMultimedia extends Application {

    private static ApplicationSingletonMultimedia sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = (ApplicationSingletonMultimedia) getApplicationContext();
    }

    public static ApplicationSingletonMultimedia getInstance() {
        return sInstance;
    }
}
