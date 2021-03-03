package com.example.meteo.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meteo.R;
import com.example.meteo.model.data.MeteoEntity;

import java.util.ArrayList;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private ArrayList<MeteoEntity> meteoEntities;

    public CityAdapter(ArrayList<MeteoEntity> meteoEntities){
        this.meteoEntities = meteoEntities;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meteo, parent, false);

        return new CityAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {

        final MeteoEntity meteoEntity = meteoEntities.get(position);

        holder.txCityName.setText(meteoEntity.city_name);


    }


    @Override
    public int getItemCount() {
        return meteoEntities.size();
    }


    protected static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvCityNameTitle;
        public TextView txCityName;




        public ViewHolder(View itemView)  {
            super(itemView);

            tvCityNameTitle = (TextView) itemView.findViewById( R.id.tv_cityNameTitle );
            txCityName = (TextView) itemView.findViewById(R.id.tx_cityName);



        }
    }
}
