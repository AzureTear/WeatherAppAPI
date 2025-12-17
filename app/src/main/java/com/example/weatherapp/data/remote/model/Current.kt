package com.example.weatherapp.data.remote.model

data class Current(
    val cloud: String,
    val condition: Condition,
    val humidity: String,
    val is_day: String,
    val temp_c: String,
    val wind_kph: String,
    val uv: String
)