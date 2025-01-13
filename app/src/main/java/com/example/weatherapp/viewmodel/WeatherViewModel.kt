package com.example.weatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.NetworkResponse
import com.example.weatherapp.data.models.WeatherModel
import com.example.weatherapp.data.remote.WeatherApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherViewModel(
    private val weatherApi: WeatherApi
) : ViewModel() {

    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>>()
    val weatherResult: LiveData<NetworkResponse<WeatherModel>> = _weatherResult


    fun fetchWeatherData(location: String) {
        _weatherResult.postValue(NetworkResponse.Loading)
        viewModelScope.launch {
            try {

                val weatherData = withContext(Dispatchers.IO) {
                    weatherApi.getWeatherData(location,7)
                }
                _weatherResult.postValue(NetworkResponse.Success(weatherData))
            } catch (e: Exception) {
                _weatherResult.postValue(NetworkResponse.Error(e.message ?: "Unknown Error"))
            }

        }
    }
}