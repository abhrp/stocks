package com.github.abhrp.stocksdemo.model

import com.google.gson.annotations.SerializedName

data class Company(
        @SerializedName("id") val id: Long,
        @SerializedName("symbol") val symbol: String,
        @SerializedName("industry") val industry: String?,
        @SerializedName("website") val website: String?,
        @SerializedName("ceo") val ceo: String?,
        @SerializedName("description") val description: String?,
        @SerializedName("sector") val sector: String?
)