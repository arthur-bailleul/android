package com.example.voleyv;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private Context ctx;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private final static String PREFS_NAME = "mon_fichier_xml";
//    private final static int PRIVATE_MODE = 0;
    private final static int PRIVATE_MODE = Context.MODE_PRIVATE;

    private final static String IS_LOGGED = "isLogged";

    private final static String ID = "keyId";
    private final static String LOGIN = "keyLogin";
    private final static String EMAIL = "keyEmail";

    public SessionManager(Context ctx) {
        this.ctx = ctx;
        preferences = ctx.getSharedPreferences(PREFS_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    public String getLogin() {
        return preferences.getString(LOGIN, null);
    }

    public String getId() {
        return preferences.getString(ID, null);
    }

    public String getEmail() {
        return preferences.getString(EMAIL, null);
    }

    public Boolean isLogged() {
        return preferences.getBoolean(IS_LOGGED, false);
    }

    public void setId(String id) {
        editor.putString(ID, id);
        editor.apply();
    }

    public void setLogin(String login) {
        editor.putString(LOGIN, login);
        editor.apply();
    }

    public void setEmail(String email) {
        editor.putString(EMAIL, email);
        editor.apply();
    }

    public  void setLogged(Boolean isLogged) {
        editor.putBoolean(IS_LOGGED, isLogged);
        editor.apply();
    }

    public void insertUser(String id, String login, String email) {
        editor.putBoolean(IS_LOGGED, true);
        editor.putString(ID, id);
        editor.putString(LOGIN, login);
        editor.putString(EMAIL, email);
        editor.apply();
    }

    public void logout() {
        editor.clear().apply();
    }
}
