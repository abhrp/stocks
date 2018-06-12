package com.github.abhrp.stocksdemo.data.remote

import com.github.abhrp.stocksdemo.network.config.RetrofitModule
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [RetrofitModule::class])
class ApiProviderModule {

    @Provides
    @Singleton
    fun stockApi(retrofit: Retrofit): StocksListingApi = retrofit.create(StocksListingApi::class.java)

    @Provides
    @Singleton
    fun companyApi(retrofit: Retrofit): StocksDetailsApi = retrofit.create(StocksDetailsApi::class.java)

}