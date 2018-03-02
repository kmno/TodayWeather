package com.todayweather.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kamran on 2/28/2018.
 */
/*
"temp": 13.1,
"pressure": 1015,
"humidity": 35,
"temp_min": 10,
"temp_max": 16
 */
public class Main {
    @SerializedName("temp")
    private double temp;
    @SerializedName("pressure")
    private int pressure;
    @SerializedName("humidity")
    private int humidity;
    @SerializedName("temp_min")
    private int temp_min;
    @SerializedName("temp_max")
    private int temp_max;

    public Main(double temp, int pressure, int humidity, int temp_min, int temp_max) {
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
    }

    public double getTemprature() {
        return temp;
    }

    public void setTemprature(double temp) {
        this.temp = temp;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getMinTemp() {
        return temp_min;
    }

    public void setMinTemp(int temp_min) {
        this.temp_min = temp_min;
    }

    public int getMaxTemp() {
        return temp_max;
    }

    public void setMaxTemp(int temp_max) {
        this.temp_max = temp_max;
    }
}
