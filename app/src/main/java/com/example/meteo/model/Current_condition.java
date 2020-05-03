package com.example.meteo.model;

public class Current_condition {

    private String date;
    private String hour;
    private Integer tmp;
    private Integer wnd_spd;
    private Integer wnd_gust;
    private String wnd_dir;
    private Float pressure;
    private Integer humidity;
    private String condition;
    private String condition_key;
    private String icon;
    private String icon_big;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public Integer getTmp() {
        return tmp;
    }

    public void setTmp(Integer tmp) {
        this.tmp = tmp;
    }

    public Integer getWnd_spd() {
        return wnd_spd;
    }

    public void setWnd_spd(Integer wnd_spd) {
        this.wnd_spd = wnd_spd;
    }

    public Integer getWnd_gust() {
        return wnd_gust;
    }

    public void setWnd_gust(Integer wnd_gust) {
        this.wnd_gust = wnd_gust;
    }

    public String getWnd_dir() {
        return wnd_dir;
    }

    public void setWnd_dir(String wnd_dir) {
        this.wnd_dir = wnd_dir;
    }

    public Float getPressure() {
        return pressure;
    }

    public void setPressure(Float pressure) {
        this.pressure = pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCondition_key() {
        return condition_key;
    }

    public void setCondition_key(String condition_key) {
        this.condition_key = condition_key;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon_big() {
        return icon_big;
    }

    public void setIcon_big(String icon_big) {
        this.icon_big = icon_big;
    }


}
