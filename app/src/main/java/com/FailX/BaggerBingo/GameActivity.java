package com.FailX.BaggerBingo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    public static final String GAME_EXTRA = "com.example.try28.ChooseActivity.game";
    private Game TheGame;
    private AttributeStrings AttributeStrings;
    private TextView name;

    private TextView haircolor_head;
    private TextView haircolor_body1;
    private TextView haircolor_body2;
    private TextView haircolor_body3;
    private View haircolor_divider;

    private TextView clothes_head;
    private TextView clothes_body1;
    private TextView clothes_body2;
    private TextView clothes_body3;
    private View clothes_divider;

    private TextView heigth_head;
    private TextView heigth_body1;
    private TextView heigth_body2;
    private TextView heigth_body3;
    private View heigth_divider;

    private TextView shirtcolor_head;
    private TextView shirtcolor_body1;
    private TextView shirtcolor_body2;
    private TextView shirtcolor_body3;
    private View shirtcolor_divider;

    private TextView flirt_text;

    private long slow_set = 250;
    private long slow = 150;                                                                               //For slowing down the rolling
    private int dice = 0;                                                                                  //Attributeindex
    private boolean stop = false;

    private int window_height;
    private int window_width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

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

        TheGame = getIntent().getParcelableExtra(GAME_EXTRA);
        LocaleHelper.setLocale(this, TheGame.getLanguage());

        AttributeStrings = new AttributeStrings(TheGame.getLanguage(), TheGame.getLevel());

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        final Button Menu = findViewById(R.id.menu_button);
        final Button Joker = findViewById(R.id.joker_button);
        final Button Win = findViewById(R.id.win_button);
        final Button Fail = findViewById(R.id.fail_button);
        final TextView Stop = findViewById(R.id.stop_btn);
        final ImageView dice_img = findViewById(R.id.dice);

        name = findViewById(R.id.name_text);

        clothes_head = findViewById(R.id.head_clothes);
        clothes_body1 = findViewById(R.id.first_body_clothes);
        clothes_body2 = findViewById(R.id.second_body_clothes);
        clothes_body3 = findViewById(R.id.third_body_clothes);
        clothes_divider = findViewById(R.id.divider13);

        haircolor_head = findViewById(R.id.head_haircolor);
        haircolor_body1 = findViewById(R.id.first_body_haircolor);
        haircolor_body2 = findViewById(R.id.second_body_haircolor);
        haircolor_body3 = findViewById(R.id.third_body_haircolor);
        haircolor_divider = findViewById(R.id.divider2);

        heigth_head = findViewById(R.id.head_heigth);
        heigth_body1 = findViewById(R.id.first_body_heigth);
        heigth_body2 = findViewById(R.id.second_body_heigth);
        heigth_body3 = findViewById(R.id.third_body_heigth);
        heigth_divider = findViewById(R.id.divider14);

        shirtcolor_head = findViewById(R.id.head_shirtcolor);
        shirtcolor_body1 = findViewById(R.id.first_body_shirtcolor);
        shirtcolor_body2 = findViewById(R.id.second_body_shirtcolor);
        shirtcolor_body3 = findViewById(R.id.third_body_shirtcolor);
        shirtcolor_divider = findViewById(R.id.divider15);

        flirt_text = findViewById(R.id.flirt_text);

        name.setText(TheGame.getCurrentPlayer().getName() + " " + getString(R.string.round) + " " + TheGame.getRound());

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        window_height = displayMetrics.heightPixels;
        window_width = displayMetrics.widthPixels;

        final SeekBar difficulty = findViewById(R.id.difficult_bar);
        difficulty.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (difficulty.getProgress() == 2) {
                    clothes_head.setVisibility(View.INVISIBLE);
                    clothes_body1.setVisibility(View.INVISIBLE);
                    clothes_body2.setVisibility(View.INVISIBLE);
                    clothes_body3.setVisibility(View.INVISIBLE);
                    clothes_divider.setVisibility(View.INVISIBLE);

                    haircolor_head.setVisibility(View.INVISIBLE);
                    haircolor_body1.setVisibility(View.INVISIBLE);
                    haircolor_body2.setVisibility(View.INVISIBLE);
                    haircolor_body3.setVisibility(View.INVISIBLE);
                    haircolor_divider.setVisibility(View.INVISIBLE);

                    ObjectAnimator heigth_head_a = ObjectAnimator.ofFloat(heigth_head, "translationY", -((float) window_height / 8));
                    ObjectAnimator heigth_body1_a = ObjectAnimator.ofFloat(heigth_body1, "translationY", -((float) window_height / 8));
                    ObjectAnimator heigth_body2_a = ObjectAnimator.ofFloat(heigth_body2, "translationY", -((float) window_height / 8));
                    ObjectAnimator heigth_body3_a = ObjectAnimator.ofFloat(heigth_body3, "translationY", -((float) window_height / 8));
                    ObjectAnimator heigth_divider_a = ObjectAnimator.ofFloat(heigth_divider, "translationY", -((float) window_height / 8));

                    ObjectAnimator shirtcolor_head_a = ObjectAnimator.ofFloat(shirtcolor_head, "translationY", -((float) window_height / 8));
                    ObjectAnimator shirtcolor_body1_a = ObjectAnimator.ofFloat(shirtcolor_body1, "translationY", -((float) window_height / 8));
                    ObjectAnimator shirtcolor_body2_a = ObjectAnimator.ofFloat(shirtcolor_body2, "translationY", -((float) window_height / 8));
                    ObjectAnimator shirtcolor_body3_a = ObjectAnimator.ofFloat(shirtcolor_body3, "translationY", -((float) window_height / 8));
                    ObjectAnimator shirtcolor_divider_a = ObjectAnimator.ofFloat(shirtcolor_divider, "translationY", -((float) window_height / 8));

                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(heigth_head_a, heigth_body1_a, heigth_body2_a, heigth_body3_a, heigth_divider_a, shirtcolor_head_a, shirtcolor_body1_a, shirtcolor_body2_a, shirtcolor_body3_a, shirtcolor_divider_a);
                    animatorSet.setDuration(200);
                    animatorSet.start();

                } else if (difficulty.getProgress() == 1) {

                    ObjectAnimator haircolor_head_a = ObjectAnimator.ofFloat(haircolor_head, "translationX", ((float) window_width / 4));
                    ObjectAnimator haircolor_body1_a = ObjectAnimator.ofFloat(haircolor_body1, "translationX", ((float) window_width / 4));
                    ObjectAnimator haircolor_body2_a = ObjectAnimator.ofFloat(haircolor_body2, "translationX", ((float) window_width / 4));
                    ObjectAnimator haircolor_body3_a = ObjectAnimator.ofFloat(haircolor_body3, "translationX", ((float) window_width / 4));
                    ObjectAnimator haircolor_divider_a = ObjectAnimator.ofFloat(haircolor_divider, "translationX", ((float) window_width / 4));

                    ObjectAnimator heigth_head_a = ObjectAnimator.ofFloat(heigth_head, "translationY", 0f);
                    ObjectAnimator heigth_body1_a = ObjectAnimator.ofFloat(heigth_body1, "translationY", 0f);
                    ObjectAnimator heigth_body2_a = ObjectAnimator.ofFloat(heigth_body2, "translationY", 0f);
                    ObjectAnimator heigth_body3_a = ObjectAnimator.ofFloat(heigth_body3, "translationY", 0f);
                    ObjectAnimator heigth_divider_a = ObjectAnimator.ofFloat(heigth_divider, "translationY", 0f);

                    ObjectAnimator shirtcolor_head_a = ObjectAnimator.ofFloat(shirtcolor_head, "translationY", 0f);
                    ObjectAnimator shirtcolor_body1_a = ObjectAnimator.ofFloat(shirtcolor_body1, "translationY", 0f);
                    ObjectAnimator shirtcolor_body2_a = ObjectAnimator.ofFloat(shirtcolor_body2, "translationY", 0f);
                    ObjectAnimator shirtcolor_body3_a = ObjectAnimator.ofFloat(shirtcolor_body3, "translationY", 0f);
                    ObjectAnimator shirtcolor_divider_a = ObjectAnimator.ofFloat(shirtcolor_divider, "translationY", 0f);

                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(haircolor_head_a, haircolor_body1_a, haircolor_body2_a, haircolor_body3_a, haircolor_divider_a, heigth_head_a, heigth_body1_a, heigth_body2_a, heigth_body3_a, heigth_divider_a, shirtcolor_head_a, shirtcolor_body1_a, shirtcolor_body2_a, shirtcolor_body3_a, shirtcolor_divider_a);
                    animatorSet.setDuration(200);
                    animatorSet.start();

                    haircolor_head.setVisibility(View.VISIBLE);
                    haircolor_body1.setVisibility(View.VISIBLE);
                    haircolor_body2.setVisibility(View.VISIBLE);
                    haircolor_body3.setVisibility(View.VISIBLE);
                    haircolor_divider.setVisibility(View.VISIBLE);

                    clothes_head.setVisibility(View.INVISIBLE);
                    clothes_body1.setVisibility(View.INVISIBLE);
                    clothes_body2.setVisibility(View.INVISIBLE);
                    clothes_body3.setVisibility(View.INVISIBLE);
                    clothes_divider.setVisibility(View.INVISIBLE);


                } else {
                    clothes_head.setVisibility(View.VISIBLE);
                    clothes_body1.setVisibility(View.VISIBLE);
                    clothes_body2.setVisibility(View.VISIBLE);
                    clothes_body3.setVisibility(View.VISIBLE);
                    clothes_divider.setVisibility(View.VISIBLE);

                    haircolor_head.setVisibility(View.VISIBLE);
                    haircolor_body1.setVisibility(View.VISIBLE);
                    haircolor_body2.setVisibility(View.VISIBLE);
                    haircolor_body3.setVisibility(View.VISIBLE);
                    haircolor_divider.setVisibility(View.VISIBLE);

                    ObjectAnimator haircolor_head_a = ObjectAnimator.ofFloat(haircolor_head, "translationX", 0f);
                    ObjectAnimator haircolor_body1_a = ObjectAnimator.ofFloat(haircolor_body1, "translationX", 0f);
                    ObjectAnimator haircolor_body2_a = ObjectAnimator.ofFloat(haircolor_body2, "translationX", 0f);
                    ObjectAnimator haircolor_body3_a = ObjectAnimator.ofFloat(haircolor_body3, "translationX", 0f);
                    ObjectAnimator haircolor_divider_a = ObjectAnimator.ofFloat(haircolor_divider, "translationX", 0f);

                    ObjectAnimator heigth_head_a = ObjectAnimator.ofFloat(heigth_head, "translationY", 0f);
                    ObjectAnimator heigth_body1_a = ObjectAnimator.ofFloat(heigth_body1, "translationY", 0f);
                    ObjectAnimator heigth_body2_a = ObjectAnimator.ofFloat(heigth_body2, "translationY", 0f);
                    ObjectAnimator heigth_body3_a = ObjectAnimator.ofFloat(heigth_body3, "translationY", 0f);
                    ObjectAnimator heigth_divider_a = ObjectAnimator.ofFloat(heigth_divider, "translationY", 0f);

                    ObjectAnimator shirtcolor_head_a = ObjectAnimator.ofFloat(shirtcolor_head, "translationY", 0f);
                    ObjectAnimator shirtcolor_body1_a = ObjectAnimator.ofFloat(shirtcolor_body1, "translationY", 0f);
                    ObjectAnimator shirtcolor_body2_a = ObjectAnimator.ofFloat(shirtcolor_body2, "translationY", 0f);
                    ObjectAnimator shirtcolor_body3_a = ObjectAnimator.ofFloat(shirtcolor_body3, "translationY", 0f);
                    ObjectAnimator shirtcolor_divider_a = ObjectAnimator.ofFloat(shirtcolor_divider, "translationY", 0f);

                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(haircolor_head_a, haircolor_body1_a, haircolor_body2_a, haircolor_body3_a, haircolor_divider_a, heigth_head_a, heigth_body1_a, heigth_body2_a, heigth_body3_a, heigth_divider_a, shirtcolor_head_a, shirtcolor_body1_a, shirtcolor_body2_a, shirtcolor_body3_a, shirtcolor_divider_a);
                    animatorSet.setDuration(200);
                    animatorSet.start();

                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });

        //Select functions start
        roll_all();                                                                                       //Creates the rolling of the attributes
        final Random rnd1 = new Random();                                                                         //Create the random dices for the attribute rolls
        final Random rnd2 = new Random();
        final Random rnd3 = new Random();
        final Random rnd4 = new Random();
        final Random rnd5 = new Random();
        //Select functions end

        //Buttons start
        Menu.setOnClickListener(new View.OnClickListener() {                                                    //Back to Menu
            @Override
            public void onClick(View v) {
                OpenMenuActivity();
            }
        });


        Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop = true;                                                                                     //Stops the roll
                int dice1 = rnd1.nextInt(3500 + 1);                                                       //Calculates a random number between 3000 and 0
                int dice2 = rnd2.nextInt(3500 + 1);                                                       //for different roll-endings in 3 seconds
                int dice3 = rnd3.nextInt(3500 + 1);
                int dice4 = rnd4.nextInt(3500 + 1);
                int dice5 = rnd5.nextInt(3500 + 1);
                Stop.setVisibility(View.INVISIBLE);
                dice_img.setVisibility(View.INVISIBLE);
                end_roll(dice1, dice2, dice3, dice4, dice5, Win, Fail, Joker);                                      //Creates the end of the rolling
            }
        });

        Joker.setText("Jokers " + TheGame.getCurrentPlayer().getJoker());                                                  //Joker Button Text
        Joker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TheGame.getCurrentPlayer().getJoker() <= 0) {                                                          //Out of Jokers Message
                    Joker.setEnabled(false);
                    Toast.makeText(getApplicationContext(), getString(R.string.toast_joker), Toast.LENGTH_SHORT).show();
                } else {
                    TheGame.getCurrentPlayer().setJoker(TheGame.getCurrentPlayer().getJoker() - 1);                            //Decrease Jokers by 1
                    Joker.setEnabled(false);
                    Joker.setText("Jokers " + TheGame.getCurrentPlayer().getJoker());
                    joker_animation(haircolor_body2);
                    joker_animation(clothes_body2);
                    joker_animation(shirtcolor_body2);
                    joker_animation(heigth_body2);
                }
            }
        });

        Win.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TheGame.getCurrentPlayer().setWin(TheGame.getCurrentPlayer().getWin() + 1);
                Joker.setEnabled(true);
                if ((TheGame.getRound() == TheGame.getMax_round()) && (TheGame.getPlayerlist().get(TheGame.getPlayerlist().size() - 1) == TheGame.getCurrentPlayer()))
                    OpenFinishActivity();
                else {
                    TheGame.setRound();
                    TheGame.nextPlayer();
                    Toast.makeText(getApplicationContext(), getString(R.string.toast_win), Toast.LENGTH_SHORT).show();
                    OpenGameActivity();
                }
            }
        });

        Fail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TheGame.getCurrentPlayer().setFail(TheGame.getCurrentPlayer().getFail() + 1);
                Joker.setEnabled(true);
                OpenFailActivity();
            }
        });

        //Buttons end
    }

    public void OpenGameActivity() {                                                                     //Opens the GameActivity
        Intent intent = new Intent(GameActivity.this, GameActivity.class);
        intent.putExtra(GAME_EXTRA, TheGame);                                                             //to pass the Game object
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void OpenFinishActivity() {                                                                   //Opens the FinishActivity (end of the Game)
        Intent intent = new Intent(this, FinishActivity.class);
        intent.putExtra(GAME_EXTRA, TheGame);                                                             //to pass the Game object
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void OpenMenuActivity() {                                                                     //Opens the MenuActivity
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra(GAME_EXTRA, TheGame);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void OpenFailActivity() {                                                                     //Opens the FailActivity
        Intent intent = new Intent(this, FailActivity.class);
        intent.putExtra(GAME_EXTRA, TheGame);                                                             //to pass the Game object
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public void finish() {
    }

    public void roll_all() {
        new CountDownTimer(30000, slow) {

            @Override
            public void onTick(long millisUntilFinished) {                                              //Creates the rotating
                if (stop) {
                    cancel();
                }

                haircolor_body1.setText(AttributeStrings.getHaircolor()[(dice + 2) % AttributeStrings.getHaircolor().length]);
                haircolor_body2.setText(AttributeStrings.getHaircolor()[(dice + 1) % AttributeStrings.getHaircolor().length]);
                haircolor_body3.setText(AttributeStrings.getHaircolor()[(dice) % AttributeStrings.getHaircolor().length]);

                clothes_body1.setText(AttributeStrings.getClothes()[(dice + 2) % AttributeStrings.getClothes().length]);
                clothes_body2.setText(AttributeStrings.getClothes()[(dice + 1) % AttributeStrings.getClothes().length]);
                clothes_body3.setText(AttributeStrings.getClothes()[(dice) % AttributeStrings.getClothes().length]);

                heigth_body1.setText((AttributeStrings.getHeight()[(dice + 2) % AttributeStrings.getHeight().length]));
                heigth_body2.setText((AttributeStrings.getHeight()[(dice + 1) % AttributeStrings.getHeight().length]));
                heigth_body3.setText((AttributeStrings.getHeight()[(dice) % AttributeStrings.getHeight().length]));

                shirtcolor_body1.setText(AttributeStrings.getAccessories()[(dice + 2) % AttributeStrings.getAccessories().length]);
                shirtcolor_body2.setText(AttributeStrings.getAccessories()[(dice + 1) % AttributeStrings.getAccessories().length]);
                shirtcolor_body3.setText(AttributeStrings.getAccessories()[(dice) % AttributeStrings.getAccessories().length]);

                flirt_text.setText(AttributeStrings.getFlirt()[(dice) % AttributeStrings.getFlirt().length]);

                dice++;                                                                                   //Increases the attributeindex
            }

            @Override
            public void onFinish() {
            }

        }.start();
    }

    public void end_roll(final int dice1, final int dice2, final int dice3, final int dice4, final int dice5, final Button Win, final Button Fail, final Button Joker) {
        dice--;
        new CountDownTimer(3500, slow_set) {
            @Override
            public void onTick(long millisUntilFinished) {                                              //Creates the rotating

                if (dice1 <= millisUntilFinished) {
                    haircolor_body1.setText(AttributeStrings.getHaircolor()[(dice + 2) % AttributeStrings.getHaircolor().length]);
                    haircolor_body2.setText(AttributeStrings.getHaircolor()[(dice + 1) % AttributeStrings.getHaircolor().length]);
                    haircolor_body3.setText(AttributeStrings.getHaircolor()[(dice) % AttributeStrings.getHaircolor().length]);
                }

                if (dice2 <= millisUntilFinished) {
                    clothes_body1.setText(AttributeStrings.getClothes()[(dice + 2) % AttributeStrings.getClothes().length]);
                    clothes_body2.setText(AttributeStrings.getClothes()[(dice + 1) % AttributeStrings.getClothes().length]);
                    clothes_body3.setText(AttributeStrings.getClothes()[(dice) % AttributeStrings.getClothes().length]);
                }
                if (dice3 <= millisUntilFinished) {
                    heigth_body1.setText((AttributeStrings.getHeight()[(dice + 2) % AttributeStrings.getHeight().length]));
                    heigth_body2.setText((AttributeStrings.getHeight()[(dice + 1) % AttributeStrings.getHeight().length]));
                    heigth_body3.setText((AttributeStrings.getHeight()[(dice) % AttributeStrings.getHeight().length]));
                }

                if (dice4 <= millisUntilFinished) {
                    shirtcolor_body1.setText(AttributeStrings.getAccessories()[(dice + 2) % AttributeStrings.getAccessories().length]);
                    shirtcolor_body2.setText(AttributeStrings.getAccessories()[(dice + 1) % AttributeStrings.getAccessories().length]);
                    shirtcolor_body3.setText(AttributeStrings.getAccessories()[(dice) % AttributeStrings.getAccessories().length]);
                }
                if (dice5 <= millisUntilFinished) {
                    flirt_text.setText(AttributeStrings.getFlirt()[(dice) % AttributeStrings.getFlirt().length]);
                }

                dice++;                                                                                   //Increases the attributeindex
                slow = slow + 400;                //Die Kacke wird nicht langsamer!
            }

            @Override
            public void onFinish() {
                if (haircolor_body2.getText() == "Joker")
                    joker_animation(haircolor_body2);
                if (clothes_body2.getText() == "Joker")
                    joker_animation(clothes_body2);
                if (heigth_body2.getText() == "Joker")
                    joker_animation(heigth_body2);
                if (shirtcolor_body2.getText() == "Joker")
                    joker_animation(shirtcolor_body2);

                stop = false;
                Win.setEnabled(true);
                Fail.setEnabled(true);
                Joker.setEnabled(true);
            }
        }.start();
    }

    public void joker_animation(final TextView joker_a) {
        joker_a.setText("Joker");
        joker_a.setBackgroundColor(Color.parseColor("#FFEB3B"));

        final ObjectAnimator spin1 = ObjectAnimator.ofFloat(joker_a, "scaleY", 1f, 1.6f);
        final ObjectAnimator spin2 = ObjectAnimator.ofFloat(joker_a, "scaleX", 1f, 1.6f);

        AnimatorSet A_set = new AnimatorSet();
        A_set.play(spin1).with(spin2);
        A_set.setDuration(1200);
        A_set.start();
    }
}