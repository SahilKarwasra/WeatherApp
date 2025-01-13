package com.example.weatherapp.di

import com.example.weatherapp.data.HttpRoutes
import com.example.weatherapp.data.remote.WeatherApi
import com.example.weatherapp.data.remote.WeatherApiImpl
import com.example.weatherapp.viewmodel.WeatherViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    single {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    encodeDefaults = true
                })
            }
            install(Logging){
                level = LogLevel.ALL
            }
        }
    }

    single {
        val client = get<HttpClient>()
        val key = HttpRoutes.API_KEY

        WeatherApiImpl(client, key) as WeatherApi
    }

    viewModelOf (::WeatherViewModel )


}