package com.github.abhrp.stocksdemo.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.PrimaryKey
import com.github.abhrp.stocksdemo.model.ChartItem

@Entity(tableName = "company", foreignKeys = [(ForeignKey(entity = StockData::class,
        parentColumns = arrayOf("symbol"),
        childColumns = arrayOf("symbol"),
        onDelete = CASCADE))]
)
data class CompanyData(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "symbol") val symbol: String,
    @ColumnInfo(name = "industry") val industry: String?,
    @ColumnInfo(name = "website") val website: String?,
    @ColumnInfo(name = "ceo") val ceo: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "sector") val sector: String?,
    @ColumnInfo(name = "chart_items") val chartItems: List<ChartItem>?
)