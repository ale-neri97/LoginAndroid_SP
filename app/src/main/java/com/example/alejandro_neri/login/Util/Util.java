package com.example.alejandro_neri.login.Util;

import android.content.SharedPreferences;

public class Util {

    // 6.1 este método se ocupa solo para traer el key value de nuestras shared preferences
    public static String getUserEmail(SharedPreferences preferences){
        //getString nos regresará el key value de email, si no tiene nada, nos regresará lo que tengamos en el segundo parametro
        return preferences.getString("email", "");
    }

    // 6.2 este método se ocupa solo para traer el key value de nuestras shared preferences
    public static String getUserPassword(SharedPreferences preferences){
        //getString nos regresará el key value de password, si no tiene nada, nos regresará lo que tengamos en el segundo parametro
        return preferences.getString("password", "");
    }
}
