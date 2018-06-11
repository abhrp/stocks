package com.github.abhrp.stocksdemo.db

import android.arch.persistence.room.Room
import android.content.Context
import com.github.abhrp.stocksdemo.application.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule(context: Context) {

    private val stockDatabase: StockDatabase = Room.databaseBuilder(context, StockDatabase::class.java, "stock-db").build()

    @ApplicationScope
    @Provides
    fun getStockDatabase(): StockDatabase = stockDatabase

    @ApplicationScope
    @Provides
    fun getStockDao(): StockDao = stockDatabase.getStockDao()

    @ApplicationScope
    @Provides
    fun getCompanyDao(): CompanyDao = stockDatabase.getCompanyDao()
}