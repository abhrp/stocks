package com.github.abhrp.stocksdemo.network.config.interfaces


import com.github.abhrp.stocksdemo.network.response.CompanyDetailsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface StocksDetailsApi {

    @GET("/stock/{symbol}/batch")
    fun getStockDetails(@Path("symbol") symbol: String, params: Map<String, Any>): Call<CompanyDetailsResponse>
}