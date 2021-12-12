package com.example.project_2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.project_2.Objects.DataManagement.User;
import com.example.project_2.Objects.Fragments.Fragment_Login;
import com.example.project_2.Objects.Fragments.Fragment_high_score;

public class Activity_Main extends AppCompatActivity {

    private Button firstFragmentBtn, secondFragmentBtn;
    private Fragment_Login fragment_login;
    private  Fragment_high_score fragment_high_score;
    private  User correctUser=new User();
    private Intent intent=getIntent();
    int points=0;


//Life_circle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        handleButtons();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    protected void onRestart(){
        super.onRestart();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }



    private void initViews(){
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, new Fragment_Login()).commit();
        firstFragmentBtn = findViewById(R.id.btnLoginFragment);
        secondFragmentBtn = findViewById(R.id.btnHighScoreFragment);

       fragment_high_score=new Fragment_high_score();
    // //  fragment_high_score.setActivity(this);
//       fragment_high_score.setCallBack

 //      fragment_login= new Fragment_Login();
  //     fragment_login.setActivity(this);
    //    fragment_login.setCallBack_User(callBack_User);

        try{
            points = intent.getIntExtra(Activity_Game.EXTRA_NUMBER, 0);
            check(points);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void check(int points){
        firstFragmentBtn.setText(String.valueOf(points));
        correctUser.setPoints(String.valueOf(points));
     }

    private void handleButtons(){
        firstFragmentBtn.setOnClickListener(v -> replaceFragment(new Fragment_high_score()));
        secondFragmentBtn.setOnClickListener(v -> replaceFragment(new Fragment_Login()));
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
    }

}
