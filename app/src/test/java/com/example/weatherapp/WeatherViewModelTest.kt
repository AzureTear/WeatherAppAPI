package com.example.weatherapp

import com.example.weatherapp.data.remote.Api.NetworkResponse
import com.example.weatherapp.data.remote.model.Condition
import com.example.weatherapp.data.remote.model.Current
import com.example.weatherapp.data.remote.model.Forecast
import com.example.weatherapp.data.remote.model.Location
import com.example.weatherapp.data.remote.model.WeatherModel
import com.example.weatherapp.data.repository.ForecastRepositoryInterface
import com.example.weatherapp.ui.viewmodel.WeatherViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import retrofit2.Response


class FakePostRepository : ForecastRepositoryInterface {
    override suspend fun getForecast(city: String): Response<WeatherModel> {
        val weatherModel = WeatherModel(
            current = Current(
                "50",
                Condition(
                    "1000",
                    "",
                    "Soleado"
                ),
                "60",
                "1",
                "20",
                "10",
                "5",
            ),
            location = Location(
                "Chile",
                "-33.45",
                "2025-12-16 23:00",
                "1734382800",
                "-70.66",
                "Santiago",
                "RM",
                "America/Santiago"
            ),
            forecast = Forecast(
                emptyList()
            )
        )

        return Response.success(weatherModel)
    }
}


@OptIn(ExperimentalCoroutinesApi::class)
class WeatherViewModelTest {

    @Test
    fun `cuando se inicializa el ViewModel carga los datos correctamente`() = runTest {
        // GIVEN
        val fakeRepository = FakePostRepository()
        val viewModel = WeatherViewModel(fakeRepository)

        // WHEN
        viewModel.getData("Santiago")

        // THEN
        val result = viewModel.weatherResult.value
        assertTrue(result is NetworkResponse.Success)

        val weatherData = (result as NetworkResponse.Success).data
        assertEquals("Santiago", weatherData.location?.name)
        assertEquals("Soleado", weatherData.current?.condition?.text)
    }
}