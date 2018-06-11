package com.github.abhrp.stocksdemo.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters

@Database(entities = [(StockData::class), (CompanyData::class)], version = 1)
@TypeConverters(ChartTypeConvertor::class)
abstract class StockDatabase: RoomDatabase() {
    abstract fun getStockDao():StockDao
    abstract fun getCompanyDao(): CompanyDao
}