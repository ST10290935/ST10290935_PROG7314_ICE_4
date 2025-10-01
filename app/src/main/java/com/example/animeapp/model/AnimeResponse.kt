package com.example.animeapp.model

import com.google.gson.annotations.SerializedName

data class AnimeResponse(
    @SerializedName("data")
    val results: List<Anime>,

    @SerializedName("meta")
    val meta: Meta
)

data class Meta(
    @SerializedName("page")
    val page: Int,

    @SerializedName("perPage")
    val perPage: Int,

    @SerializedName("totalPages")
    val totalPages: Int,

    @SerializedName("totalCount")
    val totalCount: Int
)
