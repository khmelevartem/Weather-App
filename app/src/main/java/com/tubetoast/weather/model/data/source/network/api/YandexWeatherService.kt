package com.tubetoast.weather.model.data.source.network.api

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class YandexWeatherService {

    companion object {

        const val KEY = "430c8ebf-f94b-41b5-99f1-3b8733481aab"

        val api: YandexWeatherApi = Retrofit.Builder()
            .baseUrl("https://api.weather.yandex.ru/")
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setLenient().create()
                )
            )
            .client(createOkHttpClient(ApiKeyInterceptor()))
            .build().create(YandexWeatherApi::class.java)

        private fun createOkHttpClient(interceptor: Interceptor): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
        }

        private class ApiKeyInterceptor : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                return chain.proceed(
                    chain
                    .request()
                    .newBuilder()
                    .addHeader("X-Yandex-API-Key", KEY)
                    .build()
                )
            }
        }

    }
}



