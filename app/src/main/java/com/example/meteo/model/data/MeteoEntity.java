package com.example.meteo.model.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "meteo")
public class MeteoEntity{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    public Long uid;

    @ColumnInfo(name = "city_name")
    public String city_name;
}
