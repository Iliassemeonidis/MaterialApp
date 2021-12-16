package com.example.materialapp.model.repository.picture

import com.example.materialapp.model.network.PODServerResponseData

interface RepositoryPicture {
    fun getWeatherDataFromServers(
        callback: retrofit2.Callback<PODServerResponseData>
    )
}