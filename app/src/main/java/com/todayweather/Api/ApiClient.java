package com.todayweather.Api;


import com.todayweather.Model.WeatherResponse;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.todayweather.utils.Constants.BASE_URL;

/**
 * Created by kamran on 2/28/2018.
 */

public class ApiClient {

    private static Retrofit retrofit = null;

    public interface ApiInterface {

        @GET("weather")
        Call<WeatherResponse> getWeatherDetails(@Query("q") String city,
                                                @Query("units") String unist,
                                                @Query("appid") String appid);

    }

    public static Retrofit getClient() {

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.connectTimeout(10, TimeUnit.SECONDS);
        client.readTimeout(10, TimeUnit.SECONDS);
        client.build();

        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
