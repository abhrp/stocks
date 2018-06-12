package com.github.abhrp.stocksdemo.di.modules

import android.app.Application
import android.arch.persistence.room.Room
import com.github.abhrp.stocksdemo.data.local.StockDatabase
import com.github.abhrp.stocksdemo.data.local.daos.CompanyDao
import com.github.abhrp.stocksdemo.data.local.daos.StockDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [(ApplicationModule::class)])
class DatabaseModule {

    @Singleton
    @Provides
    fun getStockDatabase(app: Application): StockDatabase = Room.databaseBuilder(app, StockDatabase::class.java, "stock-db").build()

    @Singleton
    @Provides
    fun getStockDao(stockDatabase: StockDatabase): StockDao = stockDatabase.getStockDao()

    @Singleton
    @Provides
    fun getCompanyDao(stockDatabase: StockDatabase): CompanyDao = stockDatabase.getCompanyDao()
}