package com.example.lab_1;

import android.app.Application;

import androidx.room.Room;

public class DBApp extends Application {
    public static DBApp instance;
    private AppDataBase dataBase;

    @Override
    public void onCreate(){
        super.onCreate();
        instance = this;
        dataBase = Room.databaseBuilder(this,
                AppDataBase.class, "database")
                .allowMainThreadQueries()
                .build();
    }

    public  static DBApp getInstance() {return instance;}

    public AppDataBase getDataBase() {return dataBase;}
}
