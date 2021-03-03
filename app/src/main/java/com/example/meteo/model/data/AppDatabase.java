package com.example.meteo.model.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MeteoEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MeteoDao meteoDao();
}
