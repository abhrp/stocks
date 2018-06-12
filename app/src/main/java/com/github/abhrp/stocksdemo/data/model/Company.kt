package com.github.abhrp.stocksdemo.data.model

import android.arch.persistence.room.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "company", foreignKeys = [(ForeignKey(entity = Stock::class,
        parentColumns = arrayOf("symbol"),
        childColumns = arrayOf("symbol"),
        onDelete = ForeignKey.CASCADE))],
        indices = ([(Index("symbol", unique = true))])
)
data class Company(
        @PrimaryKey @ColumnInfo(name = "id") @SerializedName("id") var id: String,
        @ColumnInfo(name = "symbol") @SerializedName("symbol") val symbol: String,
        @ColumnInfo(name = "industry") @SerializedName("industry") val industry: String,
        @ColumnInfo(name = "website") @SerializedName("website") val website: String?,
        @ColumnInfo(name = "ceo") @SerializedName("CEO") val ceo: String,
        @ColumnInfo(name = "description") @SerializedName("description") val description: String,
        @ColumnInfo(name = "sector") @SerializedName("sector") val sector: String,
        @ColumnInfo(name = "chart") var chart: List<ChartItem>?
)