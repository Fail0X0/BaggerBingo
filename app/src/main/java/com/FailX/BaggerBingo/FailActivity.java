package com.FailX.BaggerBingo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Random;

public class FailActivity extends AppCompatActivity {
    public static final String GAME_EXTRA = "com.example.try28.ChooseActivity.game";
    private Game TheGame;
    private AttributeStrings AttributeStrings;

    private TextView punishment1;
    private TextView punishment2;
    private TextView punishment3;
    private TextView punishment4;
    private TextView punishment5;
    private int dice6;
    private ImageView play_r;
    private Fade ani;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fail);

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
        LocaleHelper.setLocale(this, TheGame.getLanguage());

        AttributeStrings = new AttributeStrings(TheGame.getLanguage(), TheGame.getLevel());

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        final Button Menu = findViewById(R.id.menu_button2);

        final ImageView play_g = findViewById(R.id.play_fail1);
        play_r = findViewById(R.id.play_fail2);
        final ImageView play_b = findViewById(R.id.play_fail3);
        final ImageView play_p = findViewById(R.id.play_fail4);
        final ImageView play_lb = findViewById(R.id.play_fail5);
        final ImageView play_o = findViewById(R.id.play_fail6);

        ani = new Fade();
        ani.add(play_g);
        ani.add(play_b);
        ani.add(play_lb);
        ani.add(play_o);
        ani.add(play_p);

        punishment1 = findViewById(R.id.punishment1);
        punishment2 = findViewById(R.id.punishment2);
        punishment3 = findViewById(R.id.punishment3);
        punishment4 = findViewById(R.id.punishment4);
        punishment5 = findViewById(R.id.punishment5);


        //Select functions start
        final Random rnd6 = new Random();                                                                       //Create the random dices for shuffle
        dice6 = rnd6.nextInt(7 + 1);
        //Select functions end

        //Buttons start
        Menu.setOnClickListener(new View.OnClickListener() {                                                    //Back to Menu
            @Override
            public void onClick(View v) {
                OpenMenuActivity();
            }
        });

        play_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((TheGame.getRound() == TheGame.getMax_round()) && (TheGame.getPlayerlist().get(TheGame.getPlayerlist().size() - 1) == TheGame.getCurrentPlayer()))
                    OpenFinishActivity();
                else {
                    TheGame.setRound();
                    TheGame.nextPlayer();
                    OpenGameActivity();
                }
            }
        });

        ImClick(play_g, play_b, play_lb, play_o, play_p);
        //Buttons end

        //Textviews start
        punishment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flip(punishment1, 1);
            }
        });
        punishment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flip(punishment2, 2);
            }
        });
        punishment3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flip(punishment3, 3);
            }
        });
        punishment4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flip(punishment4, 4);
            }
        });
        punishment5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flip(punishment5, 5);
            }
        });
        //Textviews ned
    }

    public void OpenGameActivity() {                                                                     //Opens the GameActivity
        Intent intent = new Intent(FailActivity.this, GameActivity.class);
        intent.putExtra(GAME_EXTRA, TheGame);//to pass the Game object
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void OpenMenuActivity() {                                                                     //Opens the MenuActivity
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra(GAME_EXTRA, TheGame);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void OpenFinishActivity() {                                                                   //Opens the FinishActivity (end of the Game)
        Intent intent = new Intent(this, FinishActivity.class);
        intent.putExtra(GAME_EXTRA, TheGame);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public void finish() {
    }

    public void disable() {
        punishment5.setEnabled(false);
        punishment4.setEnabled(false);
        punishment3.setEnabled(false);
        punishment2.setEnabled(false);
        punishment1.setEnabled(false);
    }

    public void flip(final TextView puni, final int i) {
        final ObjectAnimator spin1 = ObjectAnimator.ofFloat(puni, "scaleY", 1f, 0f);
        final ObjectAnimator spin2 = ObjectAnimator.ofFloat(puni, "scaleY", 0f, 1f);

        spin1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                disable();
                spin2.setDuration(150);
                spin2.start();
                puni.setText(AttributeStrings.getPunishment()[(dice6 + i) % AttributeStrings.getPunishment().length]);
                puni.setBackground(getDrawable(R.drawable.purple_background));
            }
        });
        spin1.setDuration(150);
        spin1.start();
        spin2.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ani.fade_ani();
                play_r.setVisibility(View.GONE);
            }
        });
    }

    public void ImClick(ImageView im1, ImageView im2, ImageView im3, ImageView im4, ImageView im5) {
        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((TheGame.getRound() == TheGame.getMax_round()) && (TheGame.getPlayerlist().get(TheGame.getPlayerlist().size() - 1) == TheGame.getCurrentPlayer()))
                    OpenFinishActivity();
                else {
                    TheGame.setRound();
                    TheGame.nextPlayer();
                    OpenGameActivity();
                }
            }
        });
        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((TheGame.getRound() == TheGame.getMax_round()) && (TheGame.getPlayerlist().get(TheGame.getPlayerlist().size() - 1) == TheGame.getCurrentPlayer()))
                    OpenFinishActivity();
                else {
                    TheGame.setRound();
                    TheGame.nextPlayer();
                    OpenGameActivity();
                }
            }
        });
        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((TheGame.getRound() == TheGame.getMax_round()) && (TheGame.getPlayerlist().get(TheGame.getPlayerlist().size() - 1) == TheGame.getCurrentPlayer()))
                    OpenFinishActivity();
                else {
                    TheGame.setRound();
                    TheGame.nextPlayer();
                    OpenGameActivity();
                }
            }
        });
        im4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((TheGame.getRound() == TheGame.getMax_round()) && (TheGame.getPlayerlist().get(TheGame.getPlayerlist().size() - 1) == TheGame.getCurrentPlayer()))
                    OpenFinishActivity();
                else {
                    TheGame.setRound();
                    TheGame.nextPlayer();
                    OpenGameActivity();

                }
            }
        });
        im5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((TheGame.getRound() == TheGame.getMax_round()) && (TheGame.getPlayerlist().get(TheGame.getPlayerlist().size() - 1) == TheGame.getCurrentPlayer()))
                    OpenFinishActivity();
                else {
                    TheGame.setRound();
                    TheGame.nextPlayer();
                    OpenGameActivity();
                }
            }
        });
    }
}
