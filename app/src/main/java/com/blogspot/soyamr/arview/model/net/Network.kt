package com.blogspot.soyamr.notforgotagain.model.net

import com.blogspot.soyamr.arview.model.net.TaskApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object Network {
    private var token: String = "sd4grh0omdj9a31exnpikhrmsu3v46"

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder().addInterceptor { chain ->
            var request = chain.request()
            request = request.newBuilder()
                .addHeader("Accept", "application/vnd.twitchtv.v5+json")
                .addHeader("Client-ID", token)
                .build()

            chain.proceed(request)
        }.addInterceptor(
            HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        ).build()
    }


    val retrofit: TaskApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.twitch.tv/kraken/")
            .client(okHttpClient)
            .addConverterFactory(
                Json {
                    ignoreUnknownKeys = true
                }.asConverterFactory("application/json".toMediaType())
            )
            .build().create(TaskApiService::class.java)
    }
}