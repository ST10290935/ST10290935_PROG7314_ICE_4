package com.example.animeapp.network

import com.example.animeapp.api.AnimeApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://anime-db.p.rapidapi.com/"

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("X-RapidAPI-Key", "ee9da4071cmsh620a1571872cf01p19d90fjsnbe9a10bc7735")
                .addHeader("X-RapidAPI-Host", "anime-db.p.rapidapi.com")
                .build()
            chain.proceed(request)
        }
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: AnimeApiService by lazy {
        retrofit.create(AnimeApiService::class.java)
    }
}
