package com.todayweather.Presenter;

import com.todayweather.Model.WeatherResponse;

/**
 * Created by kamran on 3/2/2018.
 */

public interface Contract {

    interface View {
        void showProgress(); // display progress bar
        void hideProgress(); // hide progress bar
        void showLoadingError(String errMsg);
    }

    interface Presenter{
        void sendWeatherRequest();
        void dropView();
    }

    interface WeatherPresenterListener{
        void detailsReady(WeatherResponse wr);
    }
}
