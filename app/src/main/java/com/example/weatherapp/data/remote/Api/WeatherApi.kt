package com.example.weatherapp.data.remote.Api

import com.example.weatherapp.data.remote.model.WeatherModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("/v1/forecast.json")
    suspend fun getForecast(
        @Query("key") apikey : String,
        @Query("q") city : String,
        @Query("days") days : Int = 4
    ): Response<WeatherModel>

}