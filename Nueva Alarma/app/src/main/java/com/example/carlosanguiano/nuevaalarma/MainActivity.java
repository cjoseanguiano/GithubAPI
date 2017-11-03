package com.example.carlosanguiano.nuevaalarma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.annotation.TargetApi;
import android.app.TimePickerDialog;
import android.content.ClipboardManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    LocalData localData;

    TextView tvTime;
    int hour, min;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        localData = new LocalData(getApplicationContext());

        tvTime = findViewById(R.id.tv_reminder_time_desc);

        hour = localData.get_hour();
        min = localData.get_min();

        tvTime.setText(getFormatedTime(hour, min));
        NotificationScheduler.setReminder(MainActivity.this, AlarmReceiver.class, localData.get_hour(), localData.get_min());
        showSendHour(21,50);
    }

    private void showSendHour(int hour, int min) {
        localData.set_hour(hour);
        localData.set_min(min);
        tvTime.setText(getFormatedTime(hour, min));
        NotificationScheduler.setReminder(MainActivity.this, AlarmReceiver.class, localData.get_hour(), localData.get_min());
    }


    public String getFormatedTime(int h, int m) {
        final String OLD_FORMAT = "HH:mm";
        final String NEW_FORMAT = "hh:mm a";

        String oldDateString = h + ":" + m;
        String newDateString = "";

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT, getCurrentLocale());
            Date d = sdf.parse(oldDateString);
            sdf.applyPattern(NEW_FORMAT);
            newDateString = sdf.format(d);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newDateString;
    }

    @TargetApi(Build.VERSION_CODES.N)
    public Locale getCurrentLocale() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return getResources().getConfiguration().getLocales().get(0);
        } else {
            //noinspection deprecation
            return getResources().getConfiguration().locale;
        }
    }
}
