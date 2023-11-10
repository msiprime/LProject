package com.msicoding.lproject.core.di

import com.msicoding.lproject.data.model.WeatherResponse
import com.msicoding.lproject.data.remote.WeatherService
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val service: WeatherService) : WeatherRepository {
    override suspend fun getWeatherForLocation(location: String): WeatherResponse {
        return service.getWeather(location, "a8cb9b70848a5e283ad1978833eefa76")
    }
}