package com.example.grant.gmo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
        private long timer = 30000;
        private long timeleft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner = findViewById(R.id.spinner);
        String[] items = new String[]{"15 Seconds", "30 Seconds", "1 Minute", "2 Minutes", "5 Minutes"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);
        ProgressBar bar = findViewById(R.id.progressBar);
        bar.setMax(100);
        bar.setProgress(100);
    }
    public void ButtonOnClick(View v) {
        startTimer();
    }
    public void startTimer() {
        if (timeleft != 0) {
            return;
        }
        final ProgressBar bar = findViewById(R.id.progressBar);
        Spinner spinner = findViewById(R.id.spinner);
        String text = spinner.getSelectedItem().toString();
        if (text.indexOf("Second") != -1) {
            timer = 1000 * Long.parseLong(text.split(" ")[0]);
        } else {
            timer = 1000 * 60 * Long.parseLong(text.split(" ")[0]);
        }
        bar.setMax((int)timer);
        timeleft = new Long(timer);
        CountDownTimer countDownTimer = new CountDownTimer(timer, 1000) {
            @Override
            public void onTick(long l) {
                timeleft-=1000;
                bar.setProgress((int)timeleft);
            }
            @Override
            public void onFinish() {
                fakecall();
                vibrate();
            }
        }.start();
    }
    public void fakecall() {
        startActivity(new Intent(getApplicationContext(), CallActivity.class));
        return;
    }
    public void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);
        return;
    }
}
