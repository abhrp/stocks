package com.github.abhrp.stocksdemo.data.local.daos

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.github.abhrp.stocksdemo.data.model.Stock
import io.reactivex.Flowable


@Dao
interface StockDao {
    @Query("select * from stock")
    fun getStocks(): Flowable<List<Stock>>

    @Query("select * from stock where symbol=:symbol")
    fun getStock(symbol: String): Flowable<Stock>

    @Insert(onConflict = REPLACE)
    fun insertStock(stockData: Stock)

    @Query("delete from stock")
    fun deleteAll()
}