package com.github.abhrp.stocksdemo.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query

@Dao
interface StockDao {
    @Query("select * from stock")
    fun getStocks(): List<StockData>

    @Query("select * from stock where symbol=:symbol")
    fun getStock(symbol: String): StockData

    @Insert(onConflict = REPLACE)
    fun insertStock(stockData: StockData)

    @Query("delete from stock")
    fun deleteAll()
}