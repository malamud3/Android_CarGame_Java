package com.example.project_2;


import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.project_2.Objects.Audio.Audio;
import com.example.project_2.Objects.Audio.AudioMap;
import com.example.project_2.Objects.MainObjects.Car;
import com.example.project_2.Objects.MainObjects.Coin;
import com.example.project_2.Objects.MainObjects.Heart;
import com.example.project_2.Objects.MainObjects.Rock;
import com.google.android.material.textview.MaterialTextView;

import java.text.DecimalFormat;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class Activity_Game extends AppCompatActivity {

    public static final String EXTRA_NUMBER = "com.example.application.example.EXTRA_NUMBER";

    private final Rock rocks=new Rock(); //rocks
    private  final Car cars=new Car(); //cars
    private final Heart hearts=new Heart();//hearts
    private  final Coin coins= new Coin();//coins

    //move the car
    private ImageButton[] buttons;

    //Timer
    private Timer crateRockT = new Timer();
    private Timer moveRockT = new Timer();
    private Timer distanceT= new Timer();
    private Timer crateCoinT= new Timer();

    //sound
    private AudioMap sounds;

    //SENSOR
    public static final String SENSOR_TYPE = "SENSOR_TYPE";
    private SensorManager sensorManager;
    private Sensor accSensor;

    //distance + coins = points
    private TextView point;
    private int pointsText;

    //Life_circle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initVals();
        setUp();
        buttonsHandle();
        initSensor();
        Objects.requireNonNull(sounds.getSounds().get("welcome")).playAudio();
    }

    private void initSensor() {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }


    @Override
    protected void onStart() {
        super.onStart();
        rocks.updateRockUI();
        cars.updateCarUI();
        hearts.updateHeartUI();
        new Handler().postDelayed(this::startTicker, 3000);
        new Handler().postDelayed(() -> sounds.play("music"), 1000);
    }


    private SensorEventListener accSensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            DecimalFormat df = new DecimalFormat("##.##");
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            if(x>1.0f)//Left
                moveTheCar(0);
            if(x>1.0f) //Right
                moveTheCar(1);

            //up and down
//            if(y>1.0f)
//                moveTheCar(0);
//            if(y>1.0f)
//                moveTheCar(0);

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        stopTicker();
    }

    @Override
    protected void onResume(){
        super.onResume();
        sounds.play("music");
    }

    @Override
    protected void onPause(){
        super.onPause();
        sounds.pause("music");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Toast.makeText(Activity_Game.this,"Welcome back",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        stopTicker();
    }


    //Initializing
    private void initVals() {

        rocks.setRock(new ImageView[][]{
                {findViewById(R.id.rock_00), findViewById(R.id.rock_01), findViewById(R.id.rock_02), findViewById(R.id.rock_03), findViewById(R.id.rock_04)},
                {findViewById(R.id.rock_10), findViewById(R.id.rock_11), findViewById(R.id.rock_12), findViewById(R.id.rock_13), findViewById(R.id.rock_14)},
                {findViewById(R.id.rock_20), findViewById(R.id.rock_21), findViewById(R.id.rock_22), findViewById(R.id.rock_23), findViewById(R.id.rock_24)},
                {findViewById(R.id.rock_30), findViewById(R.id.rock_31), findViewById(R.id.rock_32), findViewById(R.id.rock_33), findViewById(R.id.rock_34)},
                {findViewById(R.id.rock_40), findViewById(R.id.rock_41), findViewById(R.id.rock_42), findViewById(R.id.rock_43), findViewById(R.id.rock_44)},
                {findViewById(R.id.rock_50), findViewById(R.id.rock_51), findViewById(R.id.rock_52), findViewById(R.id.rock_53), findViewById(R.id.rock_54)}});

        cars.setCar(new ImageView[]{
                findViewById(R.id.car_0),
                findViewById(R.id.car_1),
                findViewById(R.id.car_2),
                findViewById(R.id.car_3),
                findViewById(R.id.car_4)});


        hearts.setHearts(new ImageView[]{
                findViewById(R.id.heart_1),
                findViewById(R.id.heart_2),
                findViewById(R.id.heart_3)});


        buttons = new ImageButton[]{
                findViewById(R.id.left_button),
                findViewById(R.id.right_button)};
        //audio
        addSound();

        //points
        point=findViewById(R.id.points);
        pointsText=0;

    } //initializing data

    private void setUp() {

        rocks.initRock();
        hearts.initHeart();
        cars.initCar();

    }  //Initializing game start

    private void addSound(){
        sounds=new AudioMap();
        sounds.addAudio("welcome",new Audio(MediaPlayer.create(this,R.raw.main_audio_welcome)));
        sounds.addAudio("crash",new Audio(MediaPlayer.create(this,R.raw.main_audio_crash)));
        sounds.addAudio("move_car",new Audio(MediaPlayer.create(this,R.raw.main_audio_move_car)));
        sounds.addAudio("music",new Audio(MediaPlayer.create(this,R.raw.main_audio_music)));
    }

    private void buttonsHandle() {

        for (int i = 0; i < buttons.length; i++) {
            int finalI = i;
            buttons[i].setOnClickListener(v -> moveTheCar(finalI));
        }
    }// Initializing  buttons

    private void vibrate(int style) {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        if (style == 1)//boom
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        if (style == 2)//click
            v.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
    } //Initializing vibrate

    //game_basic
    private void moveTheCar(int d) {

        //Left
        if (d == 0 && cars.getCarTrace() != 0) {
            cars.moveCarLeft();
            sounds.play("move_car");

        }

        // Right
        else if (d == 1 && cars.getCarTrace() != 4) {
            cars.moveCarRight();
            sounds.play("move_car");
        }

        //up
        else if (d == 2) {
        }

        else if (d == 3) {
        } //down

        else {
            vibrate(2);
        }
    } // Car move logic

    // moving Rock logic
    private void moveRock2() {
        for (int i = 0; i < rocks.getRocksInGame(); i++) {
            if (rocks.getRockIndex().get(i) < 50) {// [5][0] 4 is max row
                rocks.updateRocKP(i);
            } else if (rocks.getRockIndex().get(i) >= 50) { // going to last row
                rocks.setRockFlagInIndex(rocks.getRockIndex().get(i) / 10, rocks.getRockIndex().get(i) % 10, 0); //remove old
                rocks.updateRockUI();
                moveRockToEnd2(i);

                try{rocks.getRockIndex().remove(i);} catch (Exception e) {
                    e.printStackTrace();
                }
                rocks.setRocksInGame(rocks.getRocksInGame() - 1);
                new Handler().postDelayed(cars::updateCarUI, 500);

            }
        }
    }

    private void moveRockToEnd2(int i) {
        ImageView im = cars.getCarImgByIndex(rocks.getRockIndex().get(i) % 10);
        im.setImageResource(R.drawable.main_img_rock);
        im.setVisibility(View.VISIBLE);

        if (cars.checkFlag(rocks.getRockIndex().get(i) % 10)){// crash
            handleCrash(im);
        }
    } // moving Rock to Car Row => check if Handle_Crash needed

    private void crateCoin(){
      coins.checkCoin(cars.getCarTrace());
         ImageView im = cars.getCarImgByIndex((int) coins.getCoinTrace());
        im.setImageResource(R.drawable.main_img_coin);
        im.setVisibility(View.VISIBLE);
    }

    //Ticker
    private void startTicker() {
        crateRockT = new Timer();
        crateRockT.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(rocks::crateRock);
            }
        }, 0, 1500);

        moveRockT = new Timer();
        moveRockT.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> moveRock2());
            }
        }, 500, 500);

        distanceT = new Timer();
        distanceT.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> pointByDistance());
            }
        }, 0, 1000);

        crateCoinT= new Timer();
        crateCoinT.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> crateCoin());
            }
        }, 0, 10000);
    } //start

    private void pointByDistance() {
        String text="Points: "+ (++pointsText);
        point.setText(text);
    }

    private void stopTicker() {
        crateRockT.cancel();
        moveRockT.cancel();
        distanceT.cancel();
        crateCoinT.cancel();
    } //stop

    //Handle_Crash
    private void handleCrash(@NonNull ImageView im) {
        loseLife();
        im.setImageResource(R.drawable.main_img_explosion);
        im.setVisibility(View.VISIBLE);

        sounds.play("crash");

        if(hearts.getLife()==-1){
            Toast.makeText(Activity_Game.this,"You died.. Try again",Toast.LENGTH_SHORT).show();
            newGame();
        }

        else{cars.setCarValByIndex(cars.getCarTrace(),1);}// new car
    } // crash

    private  void loseLife(){
        vibrate(1);
        hearts.loseLife();
    } // losing Heart

    private void  newGame(){
        stopTicker();
        Intent intent = new Intent(Activity_Game.this, Activity_Main.class);
        intent.putExtra(EXTRA_NUMBER, pointsText);
        startActivity(intent);
        finish();

    } // losing all Heart logic

}

