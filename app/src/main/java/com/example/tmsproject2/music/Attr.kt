package com.example.tmsproject2.music

import com.google.gson.annotations.SerializedName

data class Attr(
    @SerializedName("page") val page:Int,
    @SerializedName("perPage") val perPage: Int,
    @SerializedName("totalPages") val totalPages: Int,
    @SerializedName("total") val total: Int
)
