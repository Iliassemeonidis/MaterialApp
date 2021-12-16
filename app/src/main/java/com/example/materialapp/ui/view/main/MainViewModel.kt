package com.example.materialapp.ui.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.materialapp.model.network.PODServerResponseData
import com.example.materialapp.model.network.PictureOfTheDayData
import com.example.materialapp.model.repository.datasource.RemoteDataSource
import com.example.materialapp.model.repository.picture.RepositoryPicturempl
import retrofit2.Call
import retrofit2.Response

class MainViewModel(
    private val liveDataForViewToObserve: MutableLiveData<PictureOfTheDayData> =
        MutableLiveData(),
    private val repositoryPicture: RepositoryPicturempl = RepositoryPicturempl(
        RemoteDataSource()
    )
) : ViewModel() {

    private val callBack = object : retrofit2.Callback<PODServerResponseData> {
        override fun onResponse(
            call: Call<PODServerResponseData>,
            response: Response<PODServerResponseData>
        ) {
            if (response.isSuccessful && response.body() != null) {
                liveDataForViewToObserve.value = PictureOfTheDayData.Success(response.body()!!)
            } else {
                val message = response.message()

                if (message.isNullOrEmpty()) {
                    liveDataForViewToObserve.value =
                        PictureOfTheDayData.Error(Throwable("Unidentified error"))
                } else {
                    liveDataForViewToObserve.value =
                        PictureOfTheDayData.Error(Throwable(message))
                }
            }
        }

        override fun onFailure(call: Call<PODServerResponseData>, t: Throwable) {
            liveDataForViewToObserve.value = PictureOfTheDayData.Error(t)
        }

    }

    fun getData(): LiveData<PictureOfTheDayData> {
        sendServerRequest()
        return liveDataForViewToObserve
    }

    private fun sendServerRequest() {
        liveDataForViewToObserve.value = PictureOfTheDayData.Loading(2)
        repositoryPicture.getWeatherDataFromServers(callBack)
    }

}