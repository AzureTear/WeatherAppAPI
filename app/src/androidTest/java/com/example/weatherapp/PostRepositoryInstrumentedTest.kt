package com.example.weatherapp

import org.junit.Assert.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.weatherapp.data.repository.ForecastRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class PostRepositoryInstrumentedTest {

    @Test
    fun test_real_api_response() = runBlocking {

        val repo = ForecastRepository()

        val posts = repo.getForecast("")

        assertNotNull(posts)
    }
}