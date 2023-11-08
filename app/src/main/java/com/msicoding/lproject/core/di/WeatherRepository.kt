package com.msicoding.lproject.core.di

import com.msicoding.lproject.data.model.WeatherResponse
import com.msicoding.lproject.data.remote.WeatherService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


//object WeatherRepository {
//    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
//    private const val API_KEY = "a8cb9b70848a5e283ad1978833eefa76" // Replace with your actual API key
//
//    private val retrofit = Retrofit.Builder()
//        .baseUrl(BASE_URL)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
//    private val service = retrofit.create(WeatherService::class.java)
//
//    suspend fun getWeatherForLocation(location: String): WeatherResponse {
//        return service.getWeather(location, API_KEY)
//    }
//}

interface WeatherRepository {
    suspend fun getWeatherForLocation(location: String): WeatherResponse
}



