package com.msicoding.lproject.presention.di

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// Define the data model for the response
data class WeatherResponse(
    val main: Main,
    val name: String
)

data class Main(
    val temp: Double
)

interface WeatherService {
    @GET("weather")
    suspend fun getWeather(
        @Query("q") location: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): WeatherResponse
}

object WeatherRepository {
    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    private const val API_KEY = "a8cb9b70848a5e283ad1978833eefa76" // Replace with your actual API key

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(WeatherService::class.java)

    suspend fun getWeatherForLocation(location: String): WeatherResponse {
        return service.getWeather(location, API_KEY)
    }
}
