package com.example.weatherapp.data.remote.model

data class Current(
    val cloud: String,
    val condition: Condition,
    val feelslike_c: String,
    val feelslike_f: String,
    val gust_kph: String,
    val gust_mph: String,
    val humidity: String,
    val is_day: String,
    val precip_in: String,
    val precip_mm: String,
    val pressure_in: String,
    val pressure_mb: String,
    val temp_c: String,
    val temp_f: String,
    val wind_kph: String
)