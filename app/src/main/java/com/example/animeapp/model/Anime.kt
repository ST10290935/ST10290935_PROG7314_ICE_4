package com.example.animeapp.model

import com.google.gson.annotations.SerializedName

data class Anime(
    @SerializedName("_id")
    val id: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("image")
    val image: String?,

    @SerializedName("ranking")
    val ranking: Int?,

    @SerializedName("episodes")
    val episodes: Int?,

    @SerializedName("status")
    val status: String?,

    @SerializedName("type")
    val type: String?
)
