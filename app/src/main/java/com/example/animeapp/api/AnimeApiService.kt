package com.example.animeapp.api

import com.example.animeapp.model.AnimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AnimeApiService {
    @GET("anime")
    fun searchAnime(
        @Query("page") page: Int,      // required
        @Query("size") size: Int,      // required
        @Query("search") query: String // optional search query
    ): Call<AnimeResponse>
}
