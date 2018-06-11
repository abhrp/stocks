package com.github.abhrp.stocksdemo.model

import com.google.gson.annotations.SerializedName

data class Stock(
        @SerializedName("symbol") val symbol: String,
        @SerializedName("logo") val logo: String?,
        @SerializedName("company") val company: String?,
        @SerializedName("change") val change: Double?,
        @SerializedName("changePercent") val changePercent: Double?,
        @SerializedName("price") val price: Double?,
        @SerializedName("exchange") val exchange: String?
)