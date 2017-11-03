package com.example.carlosanguiano.nuevaalarma;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Carlos Anguiano on 02/11/17.
 * For more info contact: c.joseanguiano@gmail.com
 */

public class LocalData {

    private static final String APP_SHARED_PREFS = "RemindMePref";

    private SharedPreferences appSharedPrefs;
    private SharedPreferences.Editor prefsEditor;

    private static final String reminderStatus = "reminderStatus";
    private static final String hour = "hour";
    private static final String min = "min";

    public LocalData(Context context) {
        this.appSharedPrefs = context.getSharedPreferences(APP_SHARED_PREFS, Context.MODE_PRIVATE);
        this.prefsEditor = appSharedPrefs.edit();
    }

    public int get_hour() {
        return appSharedPrefs.getInt(hour, 20);
    }

    public void set_hour(int h) {
        prefsEditor.putInt(hour, h);
        prefsEditor.commit();
    }

    public int get_min() {
        return appSharedPrefs.getInt(min, 0);
    }

    public void set_min(int m) {
        prefsEditor.putInt(min, m);
        prefsEditor.commit();
    }
}
