package com.aulamobile.aulamobile_06_08_18;

import android.content.Context;
import android.content.SharedPreferences;

public class LoginUtil {

    private static final String LOGIN_SP = "LOGIN_SP";

    public static void save (Context context, String login, String senha){

        SharedPreferences.Editor editor = context.getSharedPreferences(LOGIN_SP, Context.MODE_PRIVATE).edit();

        editor.putString("login", login);
        editor.putString("senha", senha);
        editor.apply();
    }

    public static String get(Context context){
        SharedPreferences prefs = context.getSharedPreferences(LOGIN_SP, Context.MODE_PRIVATE);

        return prefs.getString("login", null);
    }

    public static void remove(Context context){
        SharedPreferences.Editor editor = context.getSharedPreferences(LOGIN_SP, Context.MODE_PRIVATE).edit();

        editor.remove("login");
        editor.remove("senha");
        editor.apply();
    }

}
