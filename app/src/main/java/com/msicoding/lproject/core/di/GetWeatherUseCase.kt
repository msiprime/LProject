package com.msicoding.lproject.core.di

import com.msicoding.lproject.data.model.WeatherResponse

class GetWeatherUseCase(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke(location: String): WeatherResponse {
        return weatherRepository.getWeatherForLocation(location)
    }
}
