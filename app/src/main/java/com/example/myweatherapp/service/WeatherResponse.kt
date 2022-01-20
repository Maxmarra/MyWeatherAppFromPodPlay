package com.example.myweatherapp.service

data class WeatherResponse(val main: Main) {
    data class Main(
        val temp: Double,
        val pressure: Int,
        val humidity: Int,
    )
}