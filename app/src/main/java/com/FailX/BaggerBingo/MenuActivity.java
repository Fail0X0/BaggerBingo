package com.FailX.BaggerBingo;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Locale;

public class MenuActivity extends AppCompatActivity {
    public static final String GAME_EXTRA = "com.example.try28.MenuActivity.game";
    private Game TheGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

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

        if (getIntent().getParcelableExtra(SettingsActivity.GAME_EXTRA) != null) {
            TheGame = getIntent().getParcelableExtra(SettingsActivity.GAME_EXTRA);
        } else if (getIntent().getParcelableExtra(HowToActivity.GAME_EXTRA) != null) {
            TheGame = getIntent().getParcelableExtra(HowToActivity.GAME_EXTRA);
        } else if (getIntent().getParcelableExtra(ChooseActivity.GAME_EXTRA) != null) {
            TheGame = getIntent().getParcelableExtra(ChooseActivity.GAME_EXTRA);
        } else if (getIntent().getParcelableExtra(GameActivity.GAME_EXTRA) != null) {
            TheGame = getIntent().getParcelableExtra(GameActivity.GAME_EXTRA);
        } else if (getIntent().getParcelableExtra(FailActivity.GAME_EXTRA) != null) {
            TheGame = getIntent().getParcelableExtra(FailActivity.GAME_EXTRA);
        } else if (getIntent().getParcelableExtra(FinishActivity.GAME_EXTRA) != null) {
            TheGame = getIntent().getParcelableExtra(FinishActivity.GAME_EXTRA);
        } else {
            TheGame = new Game();
        }

        TextView arrow = findViewById(R.id.arrow);

        if (TheGame.getEntry() == 1) {
            arrow.setVisibility(View.VISIBLE);
            TheGame.setEntry(0);
            if (!Locale.getDefault().getDisplayLanguage().equals("Deutsch")) {
                TheGame.setLanguage("en");
            }
        }
        else {
            arrow.setVisibility(View.GONE);
        }

        Toast.makeText(MenuActivity.this, TheGame.getLanguage(), Toast.LENGTH_SHORT).show();
        LocaleHelper.setLocale(this, TheGame.getLanguage());

        TextView name = findViewById(R.id.app_name);
        TextView settings = findViewById(R.id.settings);
        TextView library1 = findViewById(R.id.library);
        TextView play_txt = findViewById(R.id.play_txt);

        name.setText(R.string.app_name);
        settings.setText(R.string.settings);
        library1.setText(R.string.library);
        play_txt.setText(R.string.play_txt);
        arrow.setText(R.string.arrow);

        final ImageView play_g = findViewById(R.id.play_menu1);
        final ImageView play_r = findViewById(R.id.play_menu2);
        final ImageView play_b = findViewById(R.id.play_menu3);
        final ImageView play_p = findViewById(R.id.play_menu4);
        final ImageView play_lb = findViewById(R.id.play_menu5);
        final ImageView play_o = findViewById(R.id.play_menu6);

        final ImageView how_g = findViewById(R.id.how_menu1);
        final ImageView how_b = findViewById(R.id.how_menu2);
        final ImageView how_lb = findViewById(R.id.how_menu3);
        final ImageView how_o = findViewById(R.id.how_menu4);
        final ImageView how_r = findViewById(R.id.how_menu5);
        final ImageView how_p = findViewById(R.id.how_menu6);

        final ImageView settings_g = findViewById(R.id.settings_menu1);
        final ImageView settings_b = findViewById(R.id.settings_menu2);
        final ImageView settings_o = findViewById(R.id.settings_menu3);
        final ImageView settings_r = findViewById(R.id.settings_menu4);
        final ImageView settings_p = findViewById(R.id.settings_menu5);
        final ImageView settings_lb = findViewById(R.id.settings_menu6);

        final Fade ani_play = new Fade();
        ani_play.add(play_g);
        ani_play.add(play_b);
        ani_play.add(play_lb);
        ani_play.add(play_o);
        ani_play.add(play_r);
        ani_play.add(play_p);

        final Fade ani_how = new Fade();
        ani_how.add(how_g);
        ani_how.add(how_b);
        ani_how.add(how_lb);
        ani_how.add(how_o);
        ani_how.add(how_r);
        ani_how.add(how_p);

        final Fade ani_set = new Fade();
        ani_set.add(settings_g);
        ani_set.add(settings_b);
        ani_set.add(settings_lb);
        ani_set.add(settings_o);
        ani_set.add(settings_r);
        ani_set.add(settings_p);

        ani_play.fade_ani();
        ani_how.fade_ani();
        ani_set.fade_ani();

        ImClickPlay(play_g, play_b, play_lb, play_o, play_p, play_r);
        ImClickHow(how_g, how_b, how_lb, how_o, how_r, how_p);
        ImClickSet(settings_b, settings_g, settings_lb, settings_o, settings_p, settings_r);
    }

    public void OpenHowToActivity() {                                                                    //Opens the HowToActivity
        Intent intent = new Intent(MenuActivity.this, HowToActivity.class);
        intent.putExtra(GAME_EXTRA, TheGame);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void OpenChooseActivity() {                                                                   //Opens the ChooseActivity
        TheGame.getPlayerlist().clear();
        TheGame.clearRound();
        Intent intent = new Intent(MenuActivity.this, ChooseActivity.class);
        intent.putExtra(GAME_EXTRA, TheGame);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void OpenSettingsActivity() {                                                                   //Opens the ChooseActivity
        Intent intent = new Intent(MenuActivity.this, SettingsActivity.class);
        intent.putExtra(GAME_EXTRA, TheGame);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public void finish() {
    }

    public void ImClickPlay(ImageView im1, ImageView im2, ImageView im3, ImageView im4, ImageView im5, ImageView im6) {
        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenChooseActivity();
            }
        });
        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenChooseActivity();
            }
        });
        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenChooseActivity();
            }
        });
        im4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenChooseActivity();
            }
        });
        im5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenChooseActivity();
            }
        });
        im6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenChooseActivity();
            }
        });
    }

    public void ImClickHow(ImageView im1, ImageView im2, ImageView im3, ImageView im4, ImageView im5, ImageView im6) {
        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenHowToActivity();
            }
        });
        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenHowToActivity();
            }
        });
        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenHowToActivity();
            }
        });
        im4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenHowToActivity();
            }
        });
        im5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenHowToActivity();
            }
        });
        im6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenHowToActivity();
            }
        });
    }

    public void ImClickSet(ImageView im1, ImageView im2, ImageView im3, ImageView im4, ImageView im5, ImageView im6) {
        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenSettingsActivity();
            }
        });
        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenSettingsActivity();
            }
        });
        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenSettingsActivity();
            }
        });
        im4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenSettingsActivity();
            }
        });
        im5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenSettingsActivity();
            }
        });
        im6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenSettingsActivity();
            }
        });
    }
}
