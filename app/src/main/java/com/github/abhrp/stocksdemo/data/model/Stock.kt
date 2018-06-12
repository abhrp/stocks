package com.github.abhrp.stocksdemo.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "stock")
data class Stock(
        @PrimaryKey @ColumnInfo(name = "symbol") @SerializedName("symbol") val symbol: String,
        @ColumnInfo(name = "logo") @SerializedName("logo") var logo: String?,
        @ColumnInfo(name = "company") @SerializedName("companyName") val company: String,
        @ColumnInfo(name = "change") @SerializedName("change") var change: Double,
        @ColumnInfo(name = "change_percent") @SerializedName("changePercent") var changePercent: Double,
        @ColumnInfo(name = "price") @SerializedName("latestPrice") val price: Double,
        @ColumnInfo(name = "exchange") @SerializedName("primaryExchange") val exchange: String,
        @ColumnInfo(name = "low") @SerializedName("high") val high: Double,
        @ColumnInfo(name = "high") @SerializedName("low") val low: Double,
        @ColumnInfo(name = "open") @SerializedName("open") val open: Double,
        @ColumnInfo(name = "close") @SerializedName("close") val close: Double,
        @ColumnInfo(name = "isUp") var isUp: Boolean
) : Serializable