package com.msicoding.lproject.core.di
//
//import com.msicoding.lproject.data.remote.WeatherService
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import javax.inject.Singleton
//
//@InstallIn(SingletonComponent::class)
//@Module
//class RestModule {
//
//    @Provides
//    @Singleton
//    fun provideRetrofit(): Retrofit {
//        val BASE_URL = "https://api.openweathermap.org/data/2.5/"
//
//        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
//            .baseUrl(BASE_URL).build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideApi(retrofit: Retrofit): WeatherService {
//        return retrofit.create(WeatherService::class.java)
//    }
//
//}
///*
//    private const val BASE_URL = ""
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
//*/