package com.example.meteo.model.webservice;

import android.os.Build;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.RequiresApi;

import com.example.meteo.model.City_info;
import com.example.meteo.model.Current_condition;
import com.example.meteo.model.ResultatMeteo;
import com.google.gson.Gson;

import java.util.ArrayList;

public class OpenDataWS {

    private static String WS_URL = "https://www.prevision-meteo.ch/services/json/";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static ArrayList<City_info> getCityInfoDuServeur(String ville) throws Exception{
       String url = WS_URL + ville;

       String reponseEnJson = OkHttpUtils.sendGetOkHttpRequest(url);

       Gson gson = new Gson();

       ResultatMeteo resultatMeteo = gson.fromJson(reponseEnJson, ResultatMeteo.class);

       ArrayList<City_info> city_infos = new ArrayList<>();

       if(resultatMeteo ==null){
           throw new Exception("Variable resultatMeteo = null");
       }
       else if(resultatMeteo.getCity_info() != null){
           city_infos.add(resultatMeteo.getCity_info());
       }
       return city_infos;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static ArrayList<Current_condition> getCurrentConditionDuServeur(String ville) throws Exception{
        String url = WS_URL + ville;

        String reponseEnJson = OkHttpUtils.sendGetOkHttpRequest(url);

        Gson gson = new Gson();

        ResultatMeteo resultatMeteo = gson.fromJson(reponseEnJson, ResultatMeteo.class);

        ArrayList<Current_condition> current_conditions = new ArrayList<>();
        if(resultatMeteo == null){
            throw new Exception("Variable resultatMeteo = null");
        }
        else if(resultatMeteo.getCurrent_condition() != null){
            current_conditions.add(resultatMeteo.getCurrent_condition());
        }
        return current_conditions;

    }
}
