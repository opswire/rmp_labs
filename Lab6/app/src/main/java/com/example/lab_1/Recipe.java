package com.example.lab_1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import androidx.room.Entity;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

@Entity
public class Recipe {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "calorie")
    @SerializedName("Calorie")
    @Expose
    public int calorie;

    @ColumnInfo(name = "time")
    @SerializedName("Time")
    @Expose
    public int time;

    @ColumnInfo(name = "name")
    @SerializedName("Name")
    @Expose
    public String name;

    @ColumnInfo(name = "ingredients")
    @SerializedName("Ingredients")
    @Expose
    public String ingredients;

    @ColumnInfo(name = "difficulty")
    @SerializedName("Difficulty")
    @Expose
    public int difficulty;
};