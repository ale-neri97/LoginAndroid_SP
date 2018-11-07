package com.example.alejandro_neri.login.Splash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.alejandro_neri.login.Activities.LoginActivity;
import com.example.alejandro_neri.login.Activities.MainActivity;
import com.example.alejandro_neri.login.Util.Util;

public class SplashActivity extends AppCompatActivity {
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Esta es la forma en la que recuperamos las sharedPreferences
        preferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);

        // Crearemos los intents a ambas pantallas
        Intent mainIntent = new Intent(this, MainActivity.class);
        Intent loginIntent = new Intent(this, LoginActivity.class);

        String email = Util.getUserEmail(preferences);
        String password = Util.getUserPassword(preferences);

        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
            startActivity(mainIntent);
        } else{
            startActivity(loginIntent);
        }

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
