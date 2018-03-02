package com.todayweather.Presenter;

import com.todayweather.Model.WeatherResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import retrofit2.Call;
import retrofit2.Callback;

import static org.mockito.Mockito.verify;

/**
 * Created by kamran on 3/2/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class WeatherPresenterTest {

    @InjectMocks
    private WeatherPresenter presenter;

    @Mock
    private Contract.View mockedView;

    @Mock
    private Call<WeatherResponse> weatherCall;

    @Captor
    private ArgumentCaptor<Callback<WeatherResponse>> mArgumentCaptor;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void sendWeatherRequestTest() throws Exception {
        presenter.setWeatherCall(weatherCall);
        presenter.sendWeatherRequest();
        verify(mockedView).showProgress();
        verify(weatherCall).enqueue(mArgumentCaptor.capture());
        Assert.assertTrue("dsdsdsdadda", true);
    }
}