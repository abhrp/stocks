package com.github.abhrp.stocksdemo.model

import com.google.gson.annotations.SerializedName

data class ChartItem(
        @SerializedName("date") var date: String?,
        @SerializedName("open") var open: Double?,
        @SerializedName("high") var high: Double?,
        @SerializedName("low") var low: Double?,
        @SerializedName("close") var close: Double?,
        @SerializedName("volume") var volume: Long?,
        @SerializedName("change") var change: Double?,
        @SerializedName("changePercent") var changePercent: Double?,
        @SerializedName("label") var label: String?,
        @SerializedName("changeOverTime") var changeOverTime: Double?
)