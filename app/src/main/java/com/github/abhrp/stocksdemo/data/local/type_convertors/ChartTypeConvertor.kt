package com.github.abhrp.stocksdemo.data.local.type_convertors

import android.arch.persistence.room.TypeConverter
import com.github.abhrp.stocksdemo.data.model.ChartItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ChartTypeConvertor {

    private val gson = Gson()

    @TypeConverter
    fun chartItemsListToString(chartItems: List<ChartItem>?): String {
        chartItems?.let {
            return gson.toJson(chartItems)
        }
        return ""
    }

    @TypeConverter
    fun stringToChartItemsList(chartItems: String?): List<ChartItem> {
        chartItems?.let {
            val listType = object : TypeToken<List<ChartItem>>() {}.type
            return gson.fromJson(chartItems, listType)
        }
        return ArrayList<ChartItem>()
    }

}