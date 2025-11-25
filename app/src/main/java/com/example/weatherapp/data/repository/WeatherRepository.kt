package com.example.weatherapp.data.repository

import com.example.weatherapp.data.remote.Api.Constant
import com.example.weatherapp.data.remote.Api.RetrofitInstance
import com.example.weatherapp.data.remote.model.WeatherModel
import retrofit2.Response

interface ForecastRepositoryInterface {
    suspend fun getForecast(city : String): Response<WeatherModel>
}

class ForecastRepository : ForecastRepositoryInterface {
    override suspend fun getForecast(city : String) : Response<WeatherModel> {
        return RetrofitInstance.weatherApi.getForecast(Constant.apiKey, city, days = 4)
    }
}