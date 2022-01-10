package com.FailX.BaggerBingo;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class FinishActivity extends AppCompatActivity {
    public static final String GAME_EXTRA = "com.example.try28.SettingsActivity.game";
    private Game TheGame;

    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-4212746257484958/2737658722");

        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


        TheGame = getIntent().getParcelableExtra(GameActivity.GAME_EXTRA);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        LocaleHelper.setLocale(this, TheGame.getLanguage());

        TextView trophy_text = findViewById(R.id.trophy_text);
        TextView silver_text = findViewById(R.id.silver_text);
        TextView bronze_text = findViewById(R.id.bronze_text);
        TextView winner_text = findViewById(R.id.winner_text);
        Button menu = findViewById(R.id.menu_button6);

        TheGame.sortWinners();

        trophy_text.setText(TheGame.getPlayerlist().get(0).getName());

        if (TheGame.getPlayerlist().size() >= 2)
            silver_text.setText(TheGame.getPlayerlist().get(1).getName());

        if (TheGame.getPlayerlist().size() >= 3)
            bronze_text.setText(TheGame.getPlayerlist().get(2).getName());

        if ((TheGame.getPlayerlist().size()) > 1) {
            winner_text.setText(TheGame.getPlayerlist().get(0).getName() + winner_text.getText() + TheGame.getPlayerlist().get(TheGame.getPlayerlist().size() - 1).getName());
        } else {
            winner_text.setText(TheGame.getPlayerlist().get(0).getName() + winner_text.getText() + TheGame.getPlayerlist().get(0).getName());
        }
        final ObjectAnimator blink1 = ObjectAnimator.ofFloat(winner_text, View.ALPHA, 0.1f, 1f);
        blink1.setDuration(1000);
        blink1.setRepeatMode(Animation.REVERSE);
        blink1.setRepeatCount(Animation.INFINITE);
        blink1.start();

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenMenuActivity();
            }
        });
        winner_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenChooseActivity();
            }
        });
    }

    public void OpenMenuActivity() {                                                                     //Opens the MenuActivity
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra(GAME_EXTRA, TheGame);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void OpenChooseActivity() {
        TheGame.clearRound();
        TheGame.restAll();
        Intent intent = new Intent(FinishActivity.this, ChooseActivity.class);
        intent.putExtra(GAME_EXTRA, TheGame);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public void finish() {
        OpenMenuActivity();
    }
}
