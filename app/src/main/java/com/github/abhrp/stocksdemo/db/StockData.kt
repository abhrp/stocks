package com.github.abhrp.stocksdemo.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "stock")
data class StockData(
        @PrimaryKey @ColumnInfo(name = "symbol") val symbol: String,
        @ColumnInfo(name = "logo") val logo: String?,
        @ColumnInfo(name = "company") val company: String?,
        @ColumnInfo(name = "change") val change: Double?,
        @ColumnInfo(name = "change_percent") val changePercent: Double?,
        @ColumnInfo(name = "price") val price: Double?,
        @ColumnInfo(name = "exchange") val exchange: String?
)