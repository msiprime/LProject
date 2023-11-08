package com.msicoding.lproject.core.di

import com.msicoding.lproject.data.model.WeatherResponse
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(private val repository: WeatherRepository) {
    suspend operator fun invoke(location: String): WeatherResponse {
        return repository.getWeatherForLocation(location)
    }
}
