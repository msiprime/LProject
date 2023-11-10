package com.msicoding.lproject.core.di

import com.msicoding.lproject.data.model.WeatherResponse


interface WeatherRepository {
    suspend fun getWeatherForLocation(location: String): WeatherResponse
}



