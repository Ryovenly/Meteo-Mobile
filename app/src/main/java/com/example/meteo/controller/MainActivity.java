package com.example.meteo.controller;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meteo.R;
import com.example.meteo.model.City_info;
import com.example.meteo.model.Current_condition;
import com.example.meteo.model.webservice.OpenDataWS;
import com.example.meteo.view.MeteoAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvTitleDescription;
    private TextView tvIndication;
    private EditText editText;
    private Button btCity;
    private RecyclerView rv;

    private AdView mAdView;
    private AdView mAdView2;

    // Données
    private ArrayList<City_info> city_infos;
    private ArrayList<Current_condition> current_conditions;

    // Adapter
    private MeteoAdapter meteoAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitleDescription = (TextView)findViewById( R.id.tv_titleDescription );
        tvIndication = (TextView)findViewById( R.id.tv_indication );
        editText = (EditText)findViewById( R.id.editText );
        btCity = (Button)findViewById( R.id.bt_city );
        rv = findViewById(R.id.rv);

        btCity.setOnClickListener(this);

        city_infos = new ArrayList<>();
        current_conditions = new ArrayList<>();

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView2 = findViewById(R.id.adView2);
        AdRequest adRequest2 = new AdRequest.Builder().build();
        mAdView2.loadAd(adRequest2);


        meteoAdapter = new MeteoAdapter(city_infos,current_conditions);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(meteoAdapter);

    }



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {

        try {

            MyAT myAT = new MyAT();
            myAT.execute();

            Toast.makeText(this,"Good",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public class MyAT extends AsyncTask{

        private ArrayList<City_info> resultat = null;
        private ArrayList<Current_condition> resultat1 = null;
        private Exception exception = null;
        private String ville;
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                ville = editText.getText().toString();
                resultat = OpenDataWS.getCityInfoDuServeur(ville);
                resultat1 = OpenDataWS.getCurrentConditionDuServeur(ville);
            } catch (Exception e) {
                exception = e;
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            if (exception != null){
                Toast.makeText(getApplicationContext(),"erreur",Toast.LENGTH_SHORT).show();
            }
            else{
                city_infos.clear();
                city_infos.addAll(resultat);
                current_conditions.clear();
                current_conditions.addAll(resultat1);
                meteoAdapter.notifyDataSetChanged();

                Toast.makeText(getApplicationContext(),"Chargement réussi",Toast.LENGTH_SHORT).show();

            }

        }
    }
}
