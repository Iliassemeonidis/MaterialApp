package com.example.materialapp.model.repository.datasource

import com.example.materialapp.BuildConfig
import com.example.materialapp.model.MaterialApplication.Companion.getRetrofitImpl
import com.example.materialapp.model.network.PODServerResponseData
import retrofit2.Callback

class RemoteDataSource {

    fun getWeatherDetails(callback: Callback<PODServerResponseData>) {
        getRetrofitImpl().getPictureOfTheDay(BuildConfig.NASA_API_KEY).enqueue(callback)
    }
}