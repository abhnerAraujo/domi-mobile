package com.bittya.domi.services.remote

import com.bittya.domi.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor



class RetroClient (baseUrl: String){

    companion object {
        private val httpClient = OkHttpClient.Builder()

        var builder = Retrofit.Builder()
                .baseUrl(BuildConfig.Base_URL)
                .addConverterFactory(GsonConverterFactory.create())

        private var retrofit = builder
                .build()

        private val logging = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)


        fun <S> createService(serviceClass: Class<S>): S{
            if(!httpClient.interceptors().contains(logging)){
                httpClient.addInterceptor(logging)
                builder.client(httpClient.build())
                retrofit = builder.build()
            }

            return retrofit.create(serviceClass)
        }
    }

}