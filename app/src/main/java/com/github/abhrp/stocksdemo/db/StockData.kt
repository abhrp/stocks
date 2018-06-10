package com.github.abhrp.stocksdemo.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "stock")
data class StockData(
        @PrimaryKey @ColumnInfo(name = "symbol") var symbol: String,
        @ColumnInfo(name = "logi") var logo: String?,
        @ColumnInfo(name = "company") var company: String?,
        @ColumnInfo(name = "change") var change: Double?,
        @ColumnInfo(name = "change_percent") var changePercent: Double?,
        @ColumnInfo(name = "price") var price: Double?,
        @ColumnInfo(name = "exchange") var exchange: String?
) {
        constructor():this("", "", "", 0.0, 0.0, 0.0, "")
}