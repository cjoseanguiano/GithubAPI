package com.example.carlosanguiano.handlerpostdelayed;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private int counter = 0;
    private int delay = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Handler handler = new Handler();


        handler.post(new Runnable() {
            @Override
            public void run() {
                doPremethod();
                boolean wasPlacedInQue = false;
                if (counter <= 10) {
                    wasPlacedInQue = handler.postDelayed(this, delay);
                } else {
                    boolean handled = doCounterReachedMethod();
                    if (handled) {
                        return;
                    }
                }
                if (wasPlacedInQue) {
                    doOnPlacedSuccedMethod();
                } else {
                    doOnPlacedFailedMethod();
                }
            }
        });
    }

    private void doOnPlacedFailedMethod() {
        Log.i(TAG, "doOnPlacedFailedMethod:  " + String.valueOf(counter));
        counter ++;
    }

    private void doOnPlacedSuccedMethod() {
        delay = delay * 2;
    }

    private boolean doCounterReachedMethod() {
        return (System.currentTimeMillis() % 2) == 0;
    }

    private void doPremethod() {
        Log.i(TAG, "doPremethod: " + String.valueOf(counter));
    }
}
