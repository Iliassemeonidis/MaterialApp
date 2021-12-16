package com.example.materialapp.model.repository.picture

import com.example.materialapp.model.network.PODServerResponseData
import com.example.materialapp.model.repository.datasource.RemoteDataSource
import retrofit2.Callback

class RepositoryPicturempl(private val remoteDataSource: RemoteDataSource):RepositoryPicture {

    override fun getWeatherDataFromServers(callback: Callback<PODServerResponseData>) {
        remoteDataSource.getWeatherDetails(callback)
    }
}