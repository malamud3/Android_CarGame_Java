package com.example.project_2.Objects.MainObjects;

import android.view.View;
import android.widget.ImageView;

import com.example.project_2.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Rock {

    //rock
    private ImageView[][] rock; //rock GUI
    private int[][] rockFlag;// flag=0 => INVISIBLE flag=1 => VISIBLE
    private int rocksInGame;// num of rocks in game
    private Random rockStart; // starting place for rock is random
    private ArrayList<Integer> rockIndex;// example 20 => [2][0] 33 => [3][3]

    public Rock(){}

    //rock setUp => 0 rocks on map
    public  void initRock(){

        setRockFlag(new int[rock.length][rock[0].length]);
        rocksInGame = 0;
        rockStart = new Random();
        rockIndex = new ArrayList<>();

        for (int[] ints : rockFlag) {
            Arrays.fill(ints, 0);
        }

    }

    public void updateRocKP(int i){
    setRockFlagInIndex( (getRockIndex().get(i) / 10) ,(getRockIndex().get(i) % 10), 0) ; //remove old
    setRockFlagInIndex( (getRockIndex().get(i) / 10) + 1,(getRockIndex().get(i) % 10), 1) ; //new
    getRockIndex().set(i,getRockIndex().get(i) + 10);
    updateRockUI();
    }

    public void setRockFlagInIndex( int i ,int j, int value) {
        this.rockFlag[i][j] = value;
    }

    public int[][] getRockFlag() {
        return rockFlag;
    }

    public int getRocksInGame() {
        return rocksInGame;
    }

    public ArrayList<Integer> getRockIndex() {
        return rockIndex;
    }

    public void setRockFlag(int[][] rockFlag) {
        this.rockFlag = rockFlag;
    }

    public void setRocksInGame(int rocksInGame) {
        this.rocksInGame = rocksInGame;
    }

    public void setRock(ImageView[][] rock) {
        this.rock = rock;
    }


    public void updateRockUI() {
        //rock
        for (int i = 0; i < rock.length; i++) {
            for (int j = 0; j < rock[i].length; j++) {
                ImageView im = rock[i][j];
                if (rockFlag[i][j] == 0) {
                    im.setVisibility(View.INVISIBLE);
                } else if (rockFlag[i][j] == 1) {
                    im.setVisibility(View.VISIBLE);
                    im.setImageResource(R.drawable.main_img_rock);
                }
            }
        }
    }

    public void crateRock() {
       int n = rockStart.nextInt(5);
       if (rockFlag[0][n] == 0) {
           rockFlag[0][n] = 1;
           rockIndex.add(n);
        }
        rocksInGame++;
        updateRockUI();
    } // Rock crate logic

}
