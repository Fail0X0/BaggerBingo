package com.FailX.BaggerBingo;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Fade {

    private List<ImageView> im_list;


    public Fade() {
        im_list = new ArrayList<>();
    }

    public void add(ImageView im) {
        im_list.add(im);
    }

    @SuppressLint("WrongConstant")
    public void fade_ani() {

        new CountDownTimer(99999999, 2000) {
            int i = 0;
            ObjectAnimator blink1;
            ObjectAnimator blink2;

            @Override
            public void onTick(long millisUntilFinished) {

                for (int j = 0; j < im_list.size(); j++) {
                    if ((i % im_list.size()) == j) {
                        im_list.get(j).setVisibility(View.VISIBLE);
                        blink1 = ObjectAnimator.ofFloat(im_list.get(j), View.ALPHA, 0.85f, 1f);
                        blink1.setDuration(1000);
                        blink1.start();
                        blink2 = ObjectAnimator.ofFloat(im_list.get(j), View.ALPHA, 1f, 0.85f);
                        blink2.setStartDelay(1000);
                        blink2.setDuration(1000);
                        blink2.start();
                    } else
                        im_list.get(j).setVisibility(View.INVISIBLE);
                }
                i++;
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }
}
