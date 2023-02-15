package com.example.weeklyactivity5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText timeInput;
    private TextView countdown;
    private Button startButton;
    private Button incrementButton;
    private Button incrementButton1;
    private Button incrementButton10;
    private CountDownTimer timer;
    private long timeLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeInput = findViewById(R.id.time_input);
        countdown = findViewById(R.id.countdown);
        startButton = findViewById(R.id.start_button);
        incrementButton = findViewById(R.id.increment_button);
        incrementButton1 = findViewById(R.id.increment_button1);
        incrementButton10 = findViewById(R.id.increment_button10);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = timeInput.getText().toString();
                if (!TextUtils.isEmpty(input)) {
                    timeLeft = Long.parseLong(input) * 1000; // convert to milliseconds
                    startTimer();
                }
            }
        });

        incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    timeLeft += 5000;
                    updateCountdownText();
                    timer.cancel();
                    startTimer();
            }
        });
        incrementButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timeLeft += 1000;
                updateCountdownText();
                timer.cancel();
                startTimer();
            }
        });
        incrementButton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timeLeft += 10000;
                updateCountdownText();
                timer.cancel();
                startTimer();
            }
        });


    }
    private void startTimer() {
        timer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                updateCountdownText();
            }

            @Override
            public void onFinish() {
                countdown.setText("Done!");
            }
        };
        timer.start();
    }

    private void updateCountdownText() {
        int minutes = (int) (timeLeft / 1000) / 60;
        int seconds = (int) (timeLeft / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        countdown.setText(timeLeftFormatted);
    }
}