package com.example.meteo.view;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.meteo.R;
import com.example.meteo.model.City_info;
import com.example.meteo.model.Current_condition;
import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;

public class MeteoAdapter extends RecyclerView.Adapter<MeteoAdapter.ViewHolder>{


        private ArrayList<City_info> city_infos;
        private ArrayList<Current_condition> current_conditions;

        //Pointeur vers une classe qui implémente OnMeteoListener
//        private OnMeteoListener onMeteoListener;



        public MeteoAdapter(ArrayList<City_info> city_infos, ArrayList<Current_condition> current_conditions) {
            this.city_infos = city_infos;
            this.current_conditions = current_conditions;
//            this.onMeteoListener = onMeteoListener;
        }


        /**
         * Méthode qui permet de créer une ligne
         */
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meteo2, parent, false);

            return new MeteoAdapter.ViewHolder(view);
        }



    /**
         * Méthode qui remplit une ligne créée
         */
        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {

            final City_info city_info = city_infos.get(position);
            final Current_condition current_condition = current_conditions.get(position);

            holder.txCityName.setText(city_info.getName());
            holder.tvCityDate.setText(current_condition.getDate().toString());
            holder.tvHour.setText(current_condition.getHour().toString());
            holder.tvCityTemp.setText(current_condition.getTmp().toString());
            holder.tvCityCondition.setText(current_condition.getCondition());
            holder.wbIcon.loadUrl(current_condition.getIcon());


//            holder.root.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (onCity_infoListener != null) {
//                        onCity_infoListener.onClick(field);
//                    }
//                }
//            });
        }


    /**
         * Méthode qui indique le nombre de lignes à créer
         */
        @Override
        public int getItemCount() {
            return city_infos.size();
        }


        /**
         * Classe interne représentant les pointeurs vers les composants graphiques d'une ligne de la liste
         * Il y aura une instance de cette classe par ligne
         */
        protected static class ViewHolder extends RecyclerView.ViewHolder {

            public TextView tvCityNameTitle;
            public TextView txCityName;
            public TextView tvCityDateTitle;
            public TextView tvCityDate;
            public TextView tvHourTitle;
            public TextView tvHour;
            public TextView tvCityTempTitle;
            public TextView tvCityTemp;
            public TextView tvCityConditionTitle;
            public TextView tvCityCondition;
            public WebView wbIcon;
            public GoogleMap map;



            public ViewHolder(View itemView)  {
                super(itemView);

                tvCityNameTitle = (TextView) itemView.findViewById( R.id.tv_cityNameTitle );
                txCityName = (TextView) itemView.findViewById(R.id.tx_cityName);
                tvCityDateTitle = (TextView) itemView.findViewById( R.id.tv_cityDateTitle );
                tvCityDate = (TextView)itemView.findViewById( R.id.tv_cityDate );
                tvHourTitle = (TextView)itemView.findViewById(R.id.tv_HourTitle);
                tvHour = (TextView)itemView.findViewById(R.id.tv_hour);
                tvCityTempTitle = (TextView)itemView.findViewById( R.id.tv_cityTempTitle );
                tvCityTemp = (TextView)itemView.findViewById( R.id.tv_cityTemp );
                tvCityConditionTitle = (TextView)itemView.findViewById( R.id.tv_cityConditionTitle );
                tvCityCondition = (TextView)itemView.findViewById( R.id.tv_cityCondition );
                wbIcon = (WebView)itemView.findViewById(R.id.icon);


            }
        }




        //Notre moyen de communication
//        public interface OnMeteoListener {
//
//            void onClick(City_info city_info, Current_condition current_condition);
//
//        }


    }

