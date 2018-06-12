package com.github.abhrp.stocksdemo.data.remote


import com.github.abhrp.stocksdemo.data.model.Stock
import io.reactivex.Observable
import retrofit2.http.GET

interface StocksListingApi {
    @GET("stock/market/list/mostactive")
    fun getMostActiveStocks(): Observable<List<Stock>>

    @GET("stock/market/list/gainers")
    fun getGainers(): Observable<List<Stock>>

    @GET("stock/market/list/losers")
    fun getLosers(): Observable<List<Stock>>
}