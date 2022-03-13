package com.example.tmsproject2.music

import android.media.Image
import com.google.gson.annotations.SerializedName

data class Artist(
    @SerializedName("name") val name: String,
    @SerializedName("playcount") val playcount:Int,
    @SerializedName("listeners") val listeners: Int,
    @SerializedName("mbid") val mbid:String,
    @SerializedName("url") val url:String,
    @SerializedName("streamable") val streamable: Int,
    @SerializedName("image") val image: List<Image>
)
