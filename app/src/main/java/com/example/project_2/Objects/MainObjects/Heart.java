package com.example.project_2.Objects.MainObjects;

import android.view.View;
import android.widget.ImageView;

import com.example.project_2.R;

import java.util.Arrays;

public class Heart {



    //hearts
    private ImageView[] hearts;// hp GUI
    private int[] heartFlag;// flag=0 => INVISIBLE flag=1 => VISIBLE
    private int life;// hp

    public Heart(){}

    //lifeSetUp => 3 hearts
    public void initHeart(){
        setHeartFlag(new int[hearts.length]);
        Arrays.fill(heartFlag, 1);
        life=2;
    }
    public void updateHeartUI() {
        for (int i = 0; i < hearts.length; i++) {
            ImageView im = hearts[i];
            if (heartFlag[i] == 0)
                im.setVisibility(View.INVISIBLE);
            else if (heartFlag[i] == 1) {
                im.setVisibility(View.VISIBLE);
                im.setImageResource(R.drawable.main_img_heart);
            }
        }
    } // Heart GUI handle

    public  void loseLife() {
        heartFlag[life] = 0;
        life--;
        updateHeartUI();
    }

    public boolean checkFlag (int i){
        return heartFlag[i] != 0;
    }

    public  int getHeartValByIndex(int i){
        return this.heartFlag[i];
    }

    public void setHeartValByIndex(int i, int val){
        this.heartFlag[i]=val;
    }


    public int[] getHeartFlag() {
        return heartFlag;
    }

    public void setHeartFlag(int[] heartFlag) {
        this.heartFlag = heartFlag;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public ImageView[] getHearts() {
        return hearts;
    }

    public void setHearts(ImageView[] hearts) {
        this.hearts = hearts;
    }
}


