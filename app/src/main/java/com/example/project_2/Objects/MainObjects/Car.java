package com.example.project_2.Objects.MainObjects;

import android.view.View;
import android.widget.ImageView;

import com.example.project_2.R;

public class Car {


    private ImageView[] car; // car GUI
    private int[] carFlag;// flag=0 => INVISIBLE flag=1 => VISIBLE
    private int carTrace;

    public Car(){}

public void initCar(){

    carFlag = new int[car.length]; // Start in bot row mid col
    //car view in m
        for (int i = 0; i < carFlag.length; i++) {
        if (i == 2) {
            carFlag[i] = 1;
            carTrace = 2;
        } else {
            carFlag[i] = 0;
        }
    }
}


    public void moveCarLeft(){
    carFlag[carTrace] = 0;
    carTrace -= 1;
    carFlag[carTrace] = 1;
    updateCarUI();
     }

    public void moveCarRight(){
        carFlag[carTrace] = 0;
        carTrace += 1;
        carFlag[carTrace] = 1;
        updateCarUI();
    }

    public void updateCarUI() {
        //car move
        for (int i = 0; i < car.length; i++) {
            ImageView im = car[i];
            if (carFlag[i] == 0)
                im.setVisibility(View.INVISIBLE);
            else if (carFlag[i] == 1) {
                im.setVisibility(View.VISIBLE);
                im.setImageResource(R.drawable.main_img_car);
            }
        }
    }//Car GUI handle

    public boolean checkFlag (int i){
        return carFlag[i] == 1;
    }

    public  ImageView getCarImgByIndex(int i){
        return this.car[i];
    }

    public void setCarValByIndex(int i, int val){
        this.carFlag[i]=val;
    }

    public int getCarTrace() {
        return carTrace;
    }

    public void setCar(ImageView[] car) {
        this.car = car;
    }
}

