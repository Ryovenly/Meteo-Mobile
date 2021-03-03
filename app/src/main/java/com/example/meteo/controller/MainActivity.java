package com.example.meteo.controller;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meteo.R;
import com.example.meteo.model.City_info;
import com.example.meteo.model.Current_condition;
import com.example.meteo.model.data.AppDatabase;
import com.example.meteo.model.data.MeteoEntity;
import com.example.meteo.model.webservice.OpenDataWS;
import com.example.meteo.view.CityAdapter;
import com.example.meteo.view.MeteoAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvTitleDescription;
    private TextView tvIndication;
    private EditText editText;
    private Button btCity;
    private RecyclerView rv;
    private RecyclerView rv_city;

    private AdView mAdView;
    private AdView mAdView2;

    // Données
    private ArrayList<City_info> city_infos;
    private ArrayList<Current_condition> current_conditions;
    private ArrayList<MeteoEntity> meteoEntities = new ArrayList<MeteoEntity>();

    // Adapter
    private MeteoAdapter meteoAdapter;
    private CityAdapter cityAdapter;


    AppDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitleDescription = (TextView)findViewById( R.id.tv_titleDescription );
        tvIndication = (TextView)findViewById( R.id.tv_indication );
        editText = (EditText)findViewById( R.id.editText );
        btCity = (Button)findViewById( R.id.bt_city );
        rv = findViewById(R.id.rv);
        rv_city = findViewById(R.id.rv_city);

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




        // DB
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "meteo").build();


        try {

            MyAT2 myAT2 = new MyAT2();
            myAT2.execute();

            Toast.makeText(this,"En cours de chargement",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Observable.fromCallable(()->{
          meteoEntities = (ArrayList<MeteoEntity>) db.meteoDao().getLast3();
            cityAdapter = new CityAdapter((meteoEntities));
            rv_city.setLayoutManager(new LinearLayoutManager(this));
            rv_city.setAdapter(cityAdapter);





            return "done";
        }  ).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(System.out::println, Throwable::printStackTrace);




    }



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {

        try {

            MyAT myAT = new MyAT();
            myAT.execute();

            Toast.makeText(this,"En cours de chargement",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public class MyAT extends AsyncTask{

        private ArrayList<City_info> resultat = null;
        private ArrayList<Current_condition> resultat1 = null;
        private Exception exception = null;
        private String ville;
        @SuppressLint("WrongThread")
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                ville = editText.getText().toString();
                resultat = OpenDataWS.getCityInfoDuServeur(ville);
                resultat1 = OpenDataWS.getCurrentConditionDuServeur(ville);

                String cityName=resultat.get(0).getName();
                MeteoEntity meteoEntity = new MeteoEntity();
                meteoEntity.city_name = cityName;

                Observable.fromCallable(()->{
                    db.meteoDao().insertMeteo(meteoEntity);
                    List<MeteoEntity> test = db.meteoDao().getAll();

                    meteoEntities = (ArrayList<MeteoEntity>) db.meteoDao().getLast3();
                    cityAdapter.notifyDataSetChanged();

                    for (int i = 0; i<test.size(); i++){
                        Log.e("test", test.get(i).city_name);
                    }

                    return "done";
                }  ).subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(System.out::println, Throwable::printStackTrace);




                Log.e("test",cityName);
            } catch (Exception e) {
                exception = e;
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            if (exception != null){
                Toast.makeText(getApplicationContext(),"Erreur",Toast.LENGTH_SHORT).show();
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


    public class MyAT2 extends AsyncTask{

        private ArrayList<City_info> resultat = null;
        private ArrayList<Current_condition> resultat1 = null;
        private Exception exception = null;
        private String ville;
        @SuppressLint("WrongThread")
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected Object doInBackground(Object[] objects) {
            try {


                Observable.fromCallable(()->{

                  ville = db.meteoDao().getLast().get(0).city_name;
                    Log.e("test","c'est ma"+ville);

              //      ville = "Lyon";
                    resultat = OpenDataWS.getCityInfoDuServeur(ville);
                    resultat1 = OpenDataWS.getCurrentConditionDuServeur(ville);


                    String cityName=resultat.get(0).getName();

                    return "done";
                }  ).subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(System.out::println, Throwable::printStackTrace);


            } catch (Exception e) {
                exception = e;
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            if (exception != null){
                Toast.makeText(getApplicationContext(),"Erreur",Toast.LENGTH_SHORT).show();
            }
            else{

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        city_infos.clear();
                        city_infos.addAll(resultat);
                        current_conditions.clear();
                        current_conditions.addAll(resultat1);
                        meteoAdapter.notifyDataSetChanged();

                    }
                }, 5000);


                Toast.makeText(getApplicationContext(),"Chargement réussi",Toast.LENGTH_SHORT).show();

            }

        }
    }
}
