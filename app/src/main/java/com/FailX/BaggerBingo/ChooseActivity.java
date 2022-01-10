package com.FailX.BaggerBingo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
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

public class ChooseActivity extends AppCompatActivity {
    public static final String GAME_EXTRA = "com.example.try28.ChooseActivity.game";
    private Game TheGame;

    private int count = 1;
    private boolean entry = true;                                                                          //first entry
    private boolean on_change = false;                                                                          //for rename
    private TextView choice1;
    private TextView choice2;
    private TextView choice3;
    private TextView choice4;
    private TextView choice5;
    private TextView choice6;
    private TextView choice7;
    private TextView choice8;
    private TextView choice9;
    private TextView choice10;

    private ImageView play_g;
    private ImageView play_r;
    private ImageView play_b;
    private ImageView play_p;
    private ImageView play_lb;
    private ImageView play_o;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

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

        choice1 = findViewById(R.id.choose1);
        choice2 = findViewById(R.id.choose2);
        choice3 = findViewById(R.id.choose3);
        choice4 = findViewById(R.id.choose4);
        choice5 = findViewById(R.id.choose5);
        choice6 = findViewById(R.id.choose6);
        choice7 = findViewById(R.id.choose7);
        choice8 = findViewById(R.id.choose8);
        choice9 = findViewById(R.id.choose9);
        choice10 = findViewById(R.id.choose10);
        play_g = findViewById(R.id.play_choose1);
        play_r = findViewById(R.id.play_choose2);
        play_b = findViewById(R.id.play_choose3);
        play_p = findViewById(R.id.play_choose4);
        play_lb = findViewById(R.id.play_choose5);
        play_o = findViewById(R.id.play_choose6);

        final Fade ani = new Fade();
        ani.add(play_g);
        ani.add(play_b);
        ani.add(play_lb);
        ani.add(play_o);
        ani.add(play_p);

        if (getIntent().getParcelableExtra(FinishActivity.GAME_EXTRA) != null) {
            TheGame = getIntent().getParcelableExtra(FinishActivity.GAME_EXTRA);
            renew(TheGame.getPlayerlist().size(), ani);
        } else if (getIntent().getParcelableExtra(MenuActivity.GAME_EXTRA) != null) {
            TheGame = getIntent().getParcelableExtra(MenuActivity.GAME_EXTRA);
        }

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        LocaleHelper.setLocale(this, TheGame.getLanguage());

        final Button Menu = findViewById(R.id.menu_button3);
        final TextView beginner = findViewById(R.id.beginner);
        final TextView advanced = findViewById(R.id.advanced);
        final TextView expert = findViewById(R.id.expert);

        final EditText Names = findViewById(R.id.edit_name);

        //Button start
        Menu.setOnClickListener(new View.OnClickListener() {                                                    //Back to Menu
            @Override
            public void onClick(View v) {
                OpenMenuActivity();
            }
        });

        Names.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (!(Names.getText().toString().matches(""))) {
                        if (on_change) {
                            TheGame.getPlayerlist().get(count - 1).setName(Names.getText().toString());
                            on_change = false;
                        } else {
                            Gamer gamer = new Gamer(Names.getText().toString());
                            TheGame.getPlayerlist().add(gamer);
                            TheGame.getPlayerlist().get(count - 1).setJoker(TheGame.getMax_jokers());
                            if (entry) {
                                play_r.setVisibility(View.GONE);
                                ani.fade_ani();
                                entry = false;
                            }
                        }
                        NameList(count, Names.getText().toString());
                        Names.getText().clear();
                        count = TheGame.getPlayerlist().size() + 1;
                    } else
                        Toast.makeText(getApplicationContext(), getString(R.string.toast_empty), Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

        beginner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TheGame.setLevel(1);
                beginner.setTextColor(Color.parseColor("#000000"));
                advanced.setTextColor(Color.parseColor("#979494"));
                expert.setTextColor(Color.parseColor("#979494"));
            }
        });

        advanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TheGame.setLevel(2);
                beginner.setTextColor(Color.parseColor("#979494"));
                advanced.setTextColor(Color.parseColor("#000000"));
                expert.setTextColor(Color.parseColor("#979494"));
            }
        });

        expert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TheGame.setLevel(3);
                beginner.setTextColor(Color.parseColor("#979494"));
                advanced.setTextColor(Color.parseColor("#979494"));
                expert.setTextColor(Color.parseColor("#000000"));
            }
        });

        ImClick(play_g, play_b, play_lb, play_o, play_p);
        //Button end

        //Textview start
        if (!(choice1.equals(""))) {
            choice1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Names.setText(choice1.getText().subSequence(3, choice1.length()));
                    count = 1;
                    on_change = true;
                }
            });
        }
        if (!(choice2.equals(""))) {
            choice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Names.setText(choice2.getText().subSequence(3, choice2.length()));
                    count = 2;
                    on_change = true;
                }
            });
        }
        if (!(choice3.equals(""))) {
            choice3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Names.setText(choice3.getText().subSequence(3, choice3.length()));
                    count = 3;
                    on_change = true;
                }
            });
        }
        if (!(choice4.equals(""))) {
            choice4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Names.setText(choice4.getText().subSequence(3, choice4.length()));
                    count = 4;
                    on_change = true;
                }
            });
        }
        if (!(choice5.equals(""))) {
            choice5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Names.setText(choice5.getText().subSequence(3, choice5.length()));
                    count = 5;
                    on_change = true;
                }
            });
        }
        if (!(choice6.equals(""))) {
            choice6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Names.setText(choice6.getText().subSequence(3, choice6.length()));
                    count = 6;
                    on_change = true;
                }
            });
        }
        if (!(choice7.equals(""))) {
            choice7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Names.setText(choice7.getText().subSequence(3, choice7.length()));
                    count = 7;
                    on_change = true;
                }
            });
        }
        if (!(choice8.equals(""))) {
            choice8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Names.setText(choice8.getText().subSequence(3, choice8.length()));
                    count = 8;
                    on_change = true;
                }
            });
        }
        if (!(choice9.equals(""))) {
            choice9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Names.setText(choice9.getText().subSequence(3, choice9.length()));
                    count = 9;
                    on_change = true;
                }
            });
        }
        if (!(choice10.equals(""))) {
            choice10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Names.setText(choice10.getText().subSequence(4, choice10.length()));
                    count = 10;
                    on_change = true;
                }
            });
        }

        //Textview end
    }

    public void OpenGameActivity() {                                                                     //Opens the GameActivity
        Intent intent = new Intent(ChooseActivity.this, GameActivity.class);
        intent.putExtra(GAME_EXTRA, TheGame);                                                        //to pass the Game object
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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

    @SuppressLint("SetTextI18n")
    public void NameList(int count, String player_name) {
        Animation anim = AnimationUtils.loadAnimation(ChooseActivity.this, R.anim.bounce);
        switch (count) {
            case 1:
                choice1.startAnimation(anim);
                choice1.setText(count + ". " + player_name);
                choice1.setVisibility(View.VISIBLE);
                break;
            case 2:
                choice2.startAnimation(anim);
                choice2.setText(count + ". " + player_name);
                choice2.setVisibility(View.VISIBLE);
                break;
            case 3:
                choice3.startAnimation(anim);
                choice3.setText(count + ". " + player_name);
                choice3.setVisibility(View.VISIBLE);
                break;
            case 4:
                choice4.startAnimation(anim);
                choice4.setText(count + ". " + player_name);
                choice4.setVisibility(View.VISIBLE);
                break;
            case 5:
                choice5.startAnimation(anim);
                choice5.setText(count + ". " + player_name);
                choice5.setVisibility(View.VISIBLE);
                break;
            case 6:
                choice6.startAnimation(anim);
                choice6.setText(count + ". " + player_name);
                choice6.setVisibility(View.VISIBLE);
                break;
            case 7:
                choice7.startAnimation(anim);
                choice7.setText(count + ". " + player_name);
                choice7.setVisibility(View.VISIBLE);
                break;
            case 8:
                choice8.startAnimation(anim);
                choice8.setText(count + ". " + player_name);
                choice8.setVisibility(View.VISIBLE);
                break;
            case 9:
                choice9.startAnimation(anim);
                choice9.setText(count + ". " + player_name);
                choice9.setVisibility(View.VISIBLE);
                break;
            case 10:
                choice10.startAnimation(anim);
                choice10.setText(count + ". " + player_name);
                choice10.setVisibility(View.VISIBLE);
                break;
            default:
                Toast.makeText(getApplicationContext(), getString(R.string.toast_maxPlayer), Toast.LENGTH_SHORT).show();
        }
    }

    public void ImClick(ImageView im1, ImageView im2, ImageView im3, ImageView im4, ImageView im5) {
        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGameActivity();
            }
        });
        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGameActivity();
            }
        });
        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGameActivity();
            }
        });
        im4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGameActivity();
            }
        });
        im5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGameActivity();
            }
        });
    }

    void renew(int i, Fade ani) {
        entry = false;
        play_r.setVisibility(View.GONE);
        ani.fade_ani();
        count = i + 1;
        for (int j = 0; j < i; j++) {
            NameList((j + 1), TheGame.getPlayerlist().get(j).getName());
        }
    }

}