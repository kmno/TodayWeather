package com.todayweather.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kamran on 2/28/2018.
 */

/*
"id": 802,
"main": "Clouds",
"description": "scattered clouds",
"icon": "03d"
 */
public class Weather {
    @SerializedName("id")
    private int id;
    @SerializedName("main")
    private String main;
    @SerializedName("description")
    private String description;
    @SerializedName("icon")
    private String icon;

    public Weather(int id, String main, String description, String icon) {
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    public int getWid() {
        return id;
    }

    public void setWid(int id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWIcon() {
        return icon;
    }

    public void setWIcon(String icon) {
        this.icon = icon;
    }

}
