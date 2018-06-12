package com.github.abhrp.stocksdemo.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.github.abhrp.stocksdemo.data.local.daos.CompanyDao
import com.github.abhrp.stocksdemo.data.local.daos.StockDao
import com.github.abhrp.stocksdemo.data.local.type_convertors.ChartTypeConvertor
import com.github.abhrp.stocksdemo.data.model.Company
import com.github.abhrp.stocksdemo.data.model.Stock

@Database(entities = [(Stock::class), (Company::class)], version = 1)
@TypeConverters(ChartTypeConvertor::class)
abstract class StockDatabase : RoomDatabase() {
    abstract fun getStockDao(): StockDao
    abstract fun getCompanyDao(): CompanyDao
}