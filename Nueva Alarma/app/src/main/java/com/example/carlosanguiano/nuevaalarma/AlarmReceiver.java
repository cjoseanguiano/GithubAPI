package com.example.carlosanguiano.nuevaalarma;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Carlos Anguiano on 02/11/17.
 * For more info contact: c.joseanguiano@gmail.com
 */

public class AlarmReceiver extends BroadcastReceiver {


    private static final String TAG = AlarmReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction() != null && context != null) {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                Log.i(TAG, "onReceive: BOOT_COMPLETED");
                LocalData localData = new LocalData(context);
                NotificationScheduler.setReminder(context, AlarmReceiver.class, localData.get_hour(), localData.get_min());
                return;
            }
        }
        NotificationScheduler.showNotification(context, MainActivity.class, "You have 5 videos", "Watch them now");

    }
}


