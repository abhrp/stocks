package com.github.abhrp.stocksdemo.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(StockData::class), version = 1)
abstract class StockDatabase: RoomDatabase() {
    abstract fun getStockDao():StockDao
}