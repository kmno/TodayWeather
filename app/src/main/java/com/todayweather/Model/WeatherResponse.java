package com.todayweather.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

;

/**
 * Created by kamran on 2/28/2018.
 */

public class WeatherResponse {

    @SerializedName("weather")
    private List<Weather> weather;

    @SerializedName("main")
    private Main main;

    @SerializedName("name")
    public String name;

    public String getCityName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

}
