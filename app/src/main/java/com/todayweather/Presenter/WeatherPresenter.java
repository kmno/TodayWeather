package com.todayweather.Presenter;

import android.content.Context;

import com.todayweather.Api.ApiClient;
import com.todayweather.Model.WeatherResponse;
import com.todayweather.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.todayweather.utils.Constants.*;

/**
 * Created by kamran on 2/28/2018.
 */

public class WeatherPresenter implements Contract.Presenter {

    private Context ctx;
    private Contract.View mainView;
    private Contract.WeatherPresenterListener mListener;
    private Call<WeatherResponse> callForWeather;
    private WeatherResponse resp;

    public WeatherPresenter(Contract.View view,
                            Contract.WeatherPresenterListener listener,
                            Context ctx){
        this.mainView = view;
        this.mListener = listener;
        this.ctx = ctx;

        ApiClient.ApiInterface apiService =
                ApiClient.getClient().create(ApiClient.ApiInterface.class);

        callForWeather = apiService.getWeatherDetails(CITY, UNIT, API_KEY);
    }

    public void sendWeatherRequest(){

        mainView.showProgress();

        callForWeather.clone().enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {

                mainView.hideProgress();
                if(response.isSuccessful()){
                    resp = response.body();
                    if(resp != null){
                        mListener.detailsReady(resp);
                    }else{
                        mainView.showLoadingError(ctx.getString(R.string.NullResponseErrorMsg));
                    }
                }else{
                    mainView.showLoadingError(ctx.getString(R.string.FailedErrorMsg));
                }

            }

            @Override
            public void onFailure(Call<WeatherResponse>call, Throwable t) {
                mainView.hideProgress();
                mainView.showLoadingError(t.toString());
            }
        });

    }

    @Override
    public void dropView() {
        mainView = null;
    }

    //for testing purposes
    void setWeatherCall(Call<WeatherResponse> wResponse){
        callForWeather = wResponse;
    }

}
