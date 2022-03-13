package com.example.tmsproject2.music

import com.google.gson.annotations.SerializedName

data class ArtistsResponse(
    @SerializedName("artists") val artists: Artists
)
