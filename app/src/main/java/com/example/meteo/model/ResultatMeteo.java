package com.example.meteo.model;

public class ResultatMeteo {
    private City_info city_info;
    private Forecast_info forecast_info;
    private Current_condition current_condition;


    public City_info getCity_info() {
        return city_info;
    }

    public void setCity_info(City_info city_info) {
        this.city_info = city_info;
    }

    public Forecast_info getForecast_info() {
        return forecast_info;
    }

    public void setForecast_info(Forecast_info forecast_info) {
        this.forecast_info = forecast_info;
    }

    public Current_condition getCurrent_condition() {
        return current_condition;
    }

    public void setCurrent_condition(Current_condition current_condition) {
        this.current_condition = current_condition;
    }
}
