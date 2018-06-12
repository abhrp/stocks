package com.github.abhrp.stocksdemo.data.remote


import com.github.abhrp.stocksdemo.data.remote.response.CompanyDetailsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface StocksDetailsApi {
    @GET("stock/{symbol}/batch?types=quote,company,chart&range=1m&last=10")
    fun getStockDetails(@Path("symbol") symbol: String): Observable<CompanyDetailsResponse>
}