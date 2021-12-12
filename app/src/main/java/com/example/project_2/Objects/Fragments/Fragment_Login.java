package com.example.project_2.Objects.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.project_2.Activity_Game;
import com.example.project_2.Activity_Main;
import com.example.project_2.Objects.CallBack.CallBack_User;
import com.example.project_2.Objects.DataManagement.MyDB;
import com.example.project_2.Objects.DataManagement.User;
import com.example.project_2.R;

public class Fragment_Login extends Fragment {

    /* Define the UI elements */
    private EditText eName;
    private EditText ePassword;
    private Button eLogin;
    private Button eRegister;

    public static  final User user=new User();
    private final MyDB myDB= new MyDB();

    private AppCompatActivity activity;

    private CallBack_User callBack_User;

    public void setActivity(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void setCallBack_User(CallBack_User callBack_User) {
        this.callBack_User = callBack_User;
    }

        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
            View view=inflater.inflate(R.layout.freagment_login, container, false);
            initViews(view);
            btnHandle();
            // Inflate the layout for this fragment
            return view;
        }

    private void initViews(View view) {

        /* Bind the XML views to Java Code Elements */
        eName = view.findViewById(R.id.username);
        ePassword = view.findViewById(R.id.password);
        eLogin = view.findViewById(R.id.btnLogin);
        eRegister = view.findViewById(R.id.btnRegister);
    }

    private void btnHandle(){
        btnLoginHandle();
        btnRegisterHandle();
    }

    private void btnLoginHandle() {
        /* Describe the logic when the login button is clicked */
        eLogin.setOnClickListener(v -> {
             loginHandle();
           // callBack_User.onInputASent(user);
        });
    }


    private void btnRegisterHandle() {
        /* Describe the logic when the login button is clicked */
        eRegister.setOnClickListener(view -> writeToDB());
    }

    private void loginHandle(){
        /* Obtain user inputs */
        user.setUserName(eName.getText().toString());
        user.setUserPassword(ePassword.getText().toString());
        /* Check if the user inputs are empty */
        if (user.getUserName().isEmpty() || user.getUserPassword().isEmpty()) {
            /* Display a message toast to user to enter the details */
            Toast.makeText(getActivity(), "Please enter name and password!", Toast.LENGTH_LONG).show();
        } else if(checkValid()){

            /* Allow the user in to your app by going into the next activity */
            startActivity(new Intent(getActivity(), Activity_Game.class));
            getActivity().finish();
        }
    }

    private boolean checkValid(){
        myDB.readUser();
        for(User userNew : myDB.getUsers() ){

            if(user.equals(userNew))
               return true;
        }
        return false;
    }

    private void writeToDB(  ){
        /* Obtain user inputs */
        user.setUserName(eName.getText().toString());
        user.setUserPassword(ePassword.getText().toString());
        user.setPoints("0");
        if (user.getUserName().isEmpty() || user.getUserPassword().isEmpty()) {
            /* Display a message toast to user to enter the details */
            Toast.makeText(getActivity(), "Please enter name and password!", Toast.LENGTH_LONG).show();
        } else{
            // Write a message to the database
            myDB.getMyRef().child(user.getUserName()).setValue(user.getUserPassword());
            }
    }

}
