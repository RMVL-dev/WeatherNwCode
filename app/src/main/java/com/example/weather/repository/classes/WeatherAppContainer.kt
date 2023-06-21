package com.example.weather.repository.classes

import com.example.weather.network.service.WeatherService
import com.example.weather.repository.interfaces.AppContainer
import com.example.weather.repository.interfaces.WeatherRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import okhttp3.MediaType.Companion.toMediaType


class WeatherAppContainer:AppContainer {

    private val BASE_URL =
        "https://api.openweathermap.org/data/2.5/"

    @OptIn(ExperimentalSerializationApi::class)
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(Json{ignoreUnknownKeys = true}.asConverterFactory("json/application".toMediaType()))
        .build()

    private val retrofitService:WeatherService by lazy {
        retrofit.create(WeatherService::class.java)
    }

    override val weatherRepository: WeatherRepository by lazy {
        WeatherRepo(retrofit = retrofitService)
    }
}