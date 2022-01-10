package com.FailX.BaggerBingo;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class SettingsActivity extends AppCompatActivity {
    public static final String GAME_EXTRA = "com.example.try28.SettingsActivity.game";
    private Game TheGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

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

        if (getIntent().getParcelableExtra(MenuActivity.GAME_EXTRA) != null) {
            TheGame = getIntent().getParcelableExtra(MenuActivity.GAME_EXTRA);
        } else if (getIntent().getParcelableExtra(SettingsActivity.GAME_EXTRA) != null) {
            TheGame = getIntent().getParcelableExtra(SettingsActivity.GAME_EXTRA);
        }

        LocaleHelper.setLocale(this, TheGame.getLanguage());

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        Button menu = findViewById(R.id.menu_button5);
        final TextView english = findViewById(R.id.language_english);
        final TextView german = findViewById(R.id.language_german);
        final TextView rounds = findViewById(R.id.rounds);
        final TextView joker = findViewById(R.id.joker_set);
        final TextView settings_text = findViewById(R.id.setting_text);
        final Switch language_sw = findViewById(R.id.language_sw);
        final SeekBar rounds_seek = findViewById(R.id.seekBar_round);
        final SeekBar joker_seek = findViewById(R.id.seekBar_joker);

        english.setText(R.string.language_english);
        german.setText(R.string.language_german);
        settings_text.setText(R.string.setting_text);
        language_sw.setText(R.string.language_sw);
        menu.setText(R.string.menu_btn);

        if (TheGame.getLanguage().matches("de")) {
            language_sw.setChecked(false);
            german.setEnabled(true);
            english.setEnabled(false);
        } else {
            language_sw.setChecked(true);
            german.setEnabled(false);
            english.setEnabled(true);
        }

        language_sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    TheGame.setLanguage("en");
                    german.setEnabled(false);
                    english.setEnabled(true);
                } else {
                    TheGame.setLanguage("de");
                    german.setEnabled(true);
                    english.setEnabled(false);
                }
                OpenNewLanguageActivity();
            }
        });

        rounds.setText(getString(R.string.rounds) + TheGame.getMax_round());
        joker.setText(getString(R.string.joker_set) + TheGame.getMax_jokers());
        rounds_seek.setProgress(TheGame.getMax_round() - 1);
        joker_seek.setProgress(TheGame.getMax_jokers() - 1);

        rounds_seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                TheGame.setMax_round(rounds_seek.getProgress() + 1);
                rounds.setText(getString(R.string.rounds) + TheGame.getMax_round());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        joker_seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                TheGame.setMax_jokers(joker_seek.getProgress() + 1);
                joker.setText(getString(R.string.joker_set) + TheGame.getMax_jokers());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
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

    public void OpenNewLanguageActivity() {                                                                     //Opens the MenuActivity
        Intent intent = new Intent(this, SettingsActivity.class);
        intent.putExtra(GAME_EXTRA, TheGame);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        if (TheGame.getLanguage().matches("en"))
            Toast.makeText(SettingsActivity.this, "English", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(SettingsActivity.this, "Deutsch", Toast.LENGTH_SHORT).show();
    }
}
