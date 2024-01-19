package com.example.lab_1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Recipe {
    @SerializedName("Calorie")
    @Expose
    public int calorie;

    @SerializedName("Time")
    @Expose
    public int time;

    @SerializedName("Name")
    @Expose
    public String name;

    @SerializedName("Ingredients")
    @Expose
    public String ingredients;

    @SerializedName("Difficulty")
    @Expose
    public int difficulty;
};
