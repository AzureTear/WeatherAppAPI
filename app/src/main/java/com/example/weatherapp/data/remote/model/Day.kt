package com.example.weatherapp.data.remote.model

data class Day(
    val maxtemp_c: String,
    val mintemp_c: String,
    val avgtemp_c: String,
    val maxwind_kph: String,
    val avghumidity: String,
    val condition: Condition
)