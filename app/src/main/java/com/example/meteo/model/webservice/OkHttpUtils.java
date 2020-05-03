package com.example.meteo.model.webservice;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpUtils {


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String sendGetOkHttpRequest(String url) throws Exception{
        Log.v("TAG", url);
        OkHttpClient client = new OkHttpClient();

        // Création de la requête
        Request request = new Request.Builder().url(url).build();

        // Exécution de la requête
        Response response = client.newCall(request).execute();

        if (response.code() != HttpURLConnection.HTTP_OK){
            throw new Exception("Réponse du serveur incorrecte : " + response.code());
        }
        else{
            // Résultat de la requête
            return  response.body().string();
        }

    }



}
