package com.FailX.BaggerBingo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView logo = findViewById(R.id.logo_name);
        ImageView background = findViewById(R.id.background);

        final ObjectAnimator anim1 = ObjectAnimator.ofFloat(logo, "scaleY", 1f, 8f);
        final ObjectAnimator anim2 = ObjectAnimator.ofFloat(logo, "scaleX", 1f, 8f);
        AnimatorSet A_set = new AnimatorSet();
        A_set.play(anim1).with(anim2);
        A_set.setDuration(2000);
        A_set.start();

        new CountDownTimer(2000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                OpenMenuActivity();
            }

        }.start();
    }

    public void OpenMenuActivity() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}