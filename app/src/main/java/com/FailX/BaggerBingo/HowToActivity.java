package com.FailX.BaggerBingo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class HowToActivity extends AppCompatActivity {
    public static final String GAME_EXTRA = "com.example.try28.HowToActivity.game";
    private Game TheGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to);

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


        TheGame = getIntent().getParcelableExtra(MenuActivity.GAME_EXTRA);

        LocaleHelper.setLocale(this, TheGame.getLanguage());      //set language

        Button Menu_btn = findViewById(R.id.menu_button4);

        Menu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenMenuActivity();
            }
        });
    }

    public void OpenMenuActivity() {                                                                     //Opens the MenuActivity
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra(GAME_EXTRA, TheGame);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void finish() {
        OpenMenuActivity();
    }
}
