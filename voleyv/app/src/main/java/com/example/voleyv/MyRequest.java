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
    public void login(final String LOGIN, final String PASSWORD, final RetoursPHP rp) {

        String url = "http://192.168.1.254/android/login.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("PHP", response);

                try {
                    JSONObject json = new JSONObject(response);
                    var error = json.getBoolean("error");

                    var get_id = json.getInt("id");
                    var get_login = json.getString("login");
                    var get_email = json.getString("email");

                    if (!error) {
                        rp.toutOK(get_id + ";" + get_login + ";" + get_email);
                        Toast.makeText(ctx, "Vous etes connecter!", Toast.LENGTH_SHORT).show();
                    } else {
                        rp.pasOK(json.getString("message"));
                        Toast.makeText(ctx, "Erreur lors de l'inscription", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    Log.d("PHP", "passage dans le catch de login" + e);
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
                map.put("login", LOGIN);
                map.put("password", PASSWORD);

                return map;
        }};
        queue.add(request);
    }

    public void newPassword(final String LOGIN, final String OLD_PASSWORD, final String NEW_PASSWORD, final String CONFIRM_NEW_PASSWORD, final RetoursPHP rp) {
        String url = "http://192.168.1.254/android/info.php";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("PHP", response);

                try {
                    JSONObject json = new JSONObject(response);
                    var error = json.getBoolean("error");
                    var data = json.getJSONArray("login");
                    var data2 = json.getJSONObject("login");
                    var data3 = json.getString("login");

                    if (!error) {
                        rp.toutOK("Vous etes connecter!" + data.toString() + data2.toString() + data3);
                        Toast.makeText(ctx, "Vous etes connecter!", Toast.LENGTH_SHORT).show();
                    } else {
                        rp.pasOK(json.getString("message"));
                        Toast.makeText(ctx, "Erreur lors de l'inscription", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    Log.d("PHP", "passage dans le catch de login" + e);
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
                map.put("login", LOGIN);
                map.put("password", OLD_PASSWORD);
                map.put("password", NEW_PASSWORD);
                map.put("password", CONFIRM_NEW_PASSWORD);

                return map;
            }
        };
        queue.add(request);
    }
}
