package com.example.meteo.model.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MeteoDao{

    @Query("SELECT * FROM meteo")
    List<MeteoEntity> getAll();

    @Query("SELECT * FROM meteo ORDER BY uid DESC LIMIT 1")
    List<MeteoEntity> getLast();

    @Query("SELECT * FROM meteo ORDER BY uid DESC LIMIT 3")
    List<MeteoEntity> getLast3();

    @Delete
    void deleteMeteo(MeteoEntity meteoEntity);

    @Insert
    void insertMeteo(MeteoEntity meteoEntity);


}
