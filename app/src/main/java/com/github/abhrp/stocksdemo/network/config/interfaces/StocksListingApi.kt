package com.github.abhrp.stocksdemo.network.config.interfaces


import com.github.abhrp.stocksdemo.model.Stock
import retrofit2.Call
import retrofit2.http.GET

interface StocksListingApi {

    @GET("stock/market/list/mostactive")
    fun getMostActiveStocks(): Call<List<Stock>>
}