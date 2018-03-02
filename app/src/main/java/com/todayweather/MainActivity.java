package com.todayweather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.todayweather.Model.Main;
import com.todayweather.Model.Weather;
import com.todayweather.Model.WeatherResponse;
import com.todayweather.Presenter.Contract;
import com.todayweather.Presenter.WeatherPresenter;
import com.todayweather.utils.Constants;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements Contract.WeatherPresenterListener, Contract.View {

    private WeatherPresenter wPresenter;

    private ProgressBar progressBar;
    private Button retryButton;
    private LinearLayout dataContainer;
    private ImageView weatherIcon;
    private TextView currentWeatherTypeTextView;
    private TextView cityNameTextView;
    private TextView currentTempTextView;
    private TextView maxTempTextView;
    private TextView minTempTextView;
    private TextView humidityTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        retryButton = findViewById(R.id.retryButton);
        dataContainer = findViewById(R.id.dataContainer);
        weatherIcon = findViewById(R.id.weatherIcon);
        currentWeatherTypeTextView = findViewById(R.id.currentWeatherTypeTextView);
        cityNameTextView = findViewById(R.id.cityNameTextView);
        currentTempTextView = findViewById(R.id.currentTempTextView);
        maxTempTextView = findViewById(R.id.maxTempTextView);
        minTempTextView = findViewById(R.id.minTempTextView);
        humidityTextView = findViewById(R.id.humidityTextView);

        wPresenter = new WeatherPresenter(this, this, getApplicationContext());
        wPresenter.sendWeatherRequest();

        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retryButton.setVisibility(View.GONE);
                wPresenter.sendWeatherRequest();
            }
        });

    }

    @Override
    public void detailsReady(WeatherResponse wr) {

        cityNameTextView.setText(wr.getCityName());

        Main m = wr.getMain();
        currentTempTextView.setText(Math.round(m.getTemprature())+"˚");
        maxTempTextView.setText("Max : "+m.getMaxTemp()+"˚");
        minTempTextView.setText("Min : "+m.getMinTemp()+"˚");
        humidityTextView.setText("Humidity : "+m.getHumidity()+"%");

        List<Weather> weatherDetails = wr.getWeather();
        for (Weather w : weatherDetails){
            currentWeatherTypeTextView.setText(w.getMain());
            Picasso.with(getApplicationContext())
                    .load(Constants.ICON + w.getWIcon() + ".png")
                    .fit()
                    .into(weatherIcon);
        }

        showOrHideDetails(true);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingError(String errMsg) {
        retryButton.setVisibility(View.VISIBLE);
        Toast.makeText(this, errMsg,
                Toast.LENGTH_SHORT).show();
        showOrHideDetails(false);
    }

    private void showOrHideDetails(boolean flag){
        if (flag){
            dataContainer.setVisibility(View.VISIBLE);
        } else {
            dataContainer.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        wPresenter.dropView();
    }
}
