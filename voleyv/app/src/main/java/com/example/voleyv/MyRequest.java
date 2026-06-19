package com.example.voleyv;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MyRequest {
    private Context ctx;
    private RequestQueue queue;

    public MyRequest(Context ctx, RequestQueue rq) {
        this.ctx = ctx;
        this.queue = rq;
    }

    public void register(final String EMAIL, final String LOGIN, final String PASSWORD, final String CONFIRM_PASSWORD, final RetoursPHP rp) {
        String url = "http://192.168.1.254/android/register.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("PHP", "response: "+ response) ;
//                var erreurPHP = new HashMap<String, String>();

                try {
                    JSONObject json = new JSONObject(response);
                    var error = json.getBoolean("error");

                    if (!error) {
                        rp.toutOK("felicitation votre compte a ete cree");
                        Toast.makeText(ctx, "Reussite de l'inscription", Toast.LENGTH_SHORT).show();
                    } else {
                        rp.pasOK(json.getString("message"));
                        Toast.makeText(ctx, "Erreur lors de l'inscription", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
//                    throw new RuntimeException(e);
                    Log.d("PHP", "passage dans le catch de register" + e);
                    rp.systemError("Une erreur est survenue, veuillez renouveler votre essai");
                    Toast.makeText(ctx, "Probleme serveur", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               Log.d("PHP", "error: "+ error) ;
               if (error instanceof NetworkError) {
                   rp.systemError("Une erreur reseau s'est produite");
               } else if (error instanceof VolleyError) {
                  rp.systemError("Une erreur s'est produite, impossible de joindre le serveur");
               }
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                var map = new HashMap<String, String>();
                map.put("email", EMAIL);
                map.put("login", LOGIN);
                map.put("password", PASSWORD);
                map.put("confirm_password", CONFIRM_PASSWORD);

                return map;
//                return super.getParams();
            }
        };
        queue.add(request);
    }

    public interface RetoursPHP {
        void toutOK(String message);
        void pasOK(String message);
        void systemError(String message);
    }

    interface LoginCallBack {
        void onSuccess(HashMap<String, String> logIn, String message);
        void onError(String message);
        void systemError(String message);
    }

    interface NewPasswordCallBack {
        void onSuccess(String message);
        void onError(String message);
        void systemError(String message);
    }

    public void login(final String LOGIN, final String PASSWORD, final LoginCallBack callBack) {

        String url = "http://192.168.1.254/android/login.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("PHP", response);

                try {
                    JSONObject json = new JSONObject(response);
                    var error = json.getBoolean("error");

                    var map = new HashMap<String, String>();
                    map.put("id", json.getString("id"));
                    map.put("login", json.getString("login"));
                    map.put("email", json.getString("email"));


                    if (!error) {
//                        rp.toutOK(get_id + ";" + get_login + ";" + get_email);
                        callBack.onSuccess(map, "Vous etes connecter!");
                        Toast.makeText(ctx, "Vous etes connecter!", Toast.LENGTH_SHORT).show();
                    } else {
//                        rp.pasOK(json.getString("message"));
                        callBack.onError(json.getString("message"));
                        Toast.makeText(ctx, "Erreur lors de l'inscription", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("PHP", "passage dans le catch de login" + e);
//                    rp.systemError("Une erreur est survenue, veuillez renouveler votre essai");
                    callBack.systemError("Une erreur est survenue, veuillez renouveler votre essai");
                    Toast.makeText(ctx, "Probleme serveur", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("PHP", "error: "+ error) ;
                if (error instanceof NetworkError) {
                    callBack.systemError("Une erreur reseau s'est produite");
                } else if (error instanceof VolleyError) {
                    callBack.systemError("Une erreur s'est produite, impossible de joindre le serveur");
                }
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                var map = new HashMap<String, String>();
                map.put("login", LOGIN);
                map.put("password", PASSWORD);

                return map;
        }};
        queue.add(request);
    }

    // faire 2 method : Post, GET avec en param la hashmap et l'url car c'est les 2 seuls  a changer
    public void newPassword(final String LOGIN, final String OLD_PASSWORD, final String NEW_PASSWORD, final String CONFIRM_NEW_PASSWORD, final NewPasswordCallBack callBack) {
        String url = "http://192.168.1.254/android/new_password.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("PHP", response);

                try {
                    JSONObject json = new JSONObject(response);
                    var error = json.getBoolean("error");

                    if (!error) {
                        callBack.onSuccess("Vous avez changer le mot de passe");
                        Toast.makeText(ctx, "Vous etes connecter!", Toast.LENGTH_SHORT).show();
                    } else {
                        callBack.onError(json.getString("message"));
                        Toast.makeText(ctx, "Erreur lors du changement de mot de passe", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    Log.d("PHP", "passage dans le catch de login" + e);
                    callBack.systemError("Une erreur est survenue, veuillez renouveler votre essai");
                    Toast.makeText(ctx, "Probleme serveur", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("PHP", "error: "+ error) ;
                if (error instanceof NetworkError) {
                    callBack.systemError("Une erreur reseau s'est produite");
                } else if (error instanceof VolleyError) {
                    callBack.systemError("Une erreur s'est produite, impossible de joindre le serveur");
                }
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Log.d("PHP", "Map");
                var map = new HashMap<String, String>();
                map.put("login", LOGIN);
                map.put("old_password", OLD_PASSWORD);
                map.put("new_password", NEW_PASSWORD);
                map.put("confirm_new_password", CONFIRM_NEW_PASSWORD);

                Log.d("PHP", map.toString());
                return map;
            }
        };
        queue.add(request);
    }
}
