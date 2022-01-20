package com.example.myweatherapp.service

import com.example.myweatherapp.service.WeatherResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val APP_ID="0b119ac14932aba85806fc0b870975fc"

interface WeatherService {

    @GET("weather/?units=metric")
    suspend fun searchWeatherByName(
        @Query("q") query: String,
        @Query("appid") appid: String = APP_ID,
        ): Response<WeatherResponse>


    companion object {

        val instance: WeatherService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(WeatherService::class.java)
        }
    }
}