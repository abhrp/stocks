package com.github.abhrp.stocksdemo.data.model

import com.google.gson.annotations.SerializedName

data class ChartItem(
        @SerializedName("date") val date: String,
        @SerializedName("open") val open: Double,
        @SerializedName("high") val high: Double,
        @SerializedName("low") val low: Double,
        @SerializedName("close") val close: Double,
        @SerializedName("volume") val volume: Long?,
        @SerializedName("change") val change: Double?,
        @SerializedName("changePercent") val changePercent: Double?,
        @SerializedName("label") val label: String,
        @SerializedName("changeOverTime") val changeOverTime: Double?
)