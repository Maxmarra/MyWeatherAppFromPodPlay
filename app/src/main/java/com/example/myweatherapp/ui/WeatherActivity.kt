package com.example.myweatherapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myweatherapp.R
import com.example.myweatherapp.repository.WeatherRepo
import com.example.myweatherapp.service.WeatherService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WeatherActivity : AppCompatActivity() {

    val TAG = javaClass.simpleName


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        val weatherService = WeatherService.instance
        val weatherRepo = WeatherRepo(weatherService)

        GlobalScope.launch {
//            val results = weatherRepo.getWeatherForecastRepo("London")
//            Log.d(TAG, "Results = ${results.body()}")

            val results = weatherRepo.searchWeatherRepo("London")
            Log.d(TAG, "Results = ${results.body()}")
            }
    }
}