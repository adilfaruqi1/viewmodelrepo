package com.mythio.retrofitsample.responsemodel

import com.google.gson.annotations.SerializedName

data class VehicleMake(
    @SerializedName("make")
    val makes: List<Make>
)

data class Make(
    @SerializedName("id") val id : Int,
    @SerializedName("Name") val name : String,
    @SerializedName("Location") val location : String,
    @SerializedName("IconFile") val iconFile : String,
    @SerializedName("Rank") val rank : String,
    @SerializedName("Status") val status : Int,
    @SerializedName("created_at") val created_at : String,
    @SerializedName("updated_at") val updated_at : String
)