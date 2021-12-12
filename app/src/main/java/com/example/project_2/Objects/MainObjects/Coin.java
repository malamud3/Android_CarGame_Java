package com.example.project_2.Objects.MainObjects;

import android.view.View;
import android.widget.ImageView;

import com.example.project_2.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Coin {
    private int CoinTrace;
    public  Coin(){}

    public void checkCoin(int carTrace){
        Random n = new Random();
        int CoinTrace= n.nextInt(5);
        while (carTrace==CoinTrace){
            n.nextInt(5);
        }
    }

    public int getCoinTrace() {
        return CoinTrace;
    }

    public void setCoinTrace(int coinTrace) {
        CoinTrace = coinTrace;
    }
}