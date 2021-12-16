package com.example.materialapp.model

import android.app.Application
import com.example.materialapp.model.repository.datasource.PictureOfTheDayAPI
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okio.IOException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MaterialApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        private var appInstance: MaterialApplication? = null
        private var retrofit: Retrofit? = null

        private const val baseUrl = "https://api.nasa.gov/"

        fun getRetrofitImpl(): PictureOfTheDayAPI {
            if (retrofit == null) {
                synchronized(Retrofit::class.java) {
                    if (appInstance == null) throw IllegalStateException("Application is null while creating Retrofit")
                    retrofit = Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(
                            GsonConverterFactory.create(
                                GsonBuilder().setLenient().create()
                            )
                        )
                        .client(createOkHttpClient(PODInterceptor()))
                        .build()
                }
            }
            return retrofit!!.create(PictureOfTheDayAPI::class.java)
        }

        private fun createOkHttpClient(interceptor: Interceptor): OkHttpClient {
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(interceptor)
            httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            return httpClient.build()
        }

        class PODInterceptor : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                return chain.proceed(chain.request())
            }
        }
    }
}