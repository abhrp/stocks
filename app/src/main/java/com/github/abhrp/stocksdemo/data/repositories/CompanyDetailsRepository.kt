package com.github.abhrp.stocksdemo.data.repositories

import com.github.abhrp.stocksdemo.data.local.daos.CompanyDao
import com.github.abhrp.stocksdemo.data.model.Company
import com.github.abhrp.stocksdemo.data.remote.StocksDetailsApi
import com.github.abhrp.stocksdemo.data.remote.response.CompanyDetailsResponse
import com.github.abhrp.stocksdemo.util.Utils
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CompanyDetailsRepository @Inject constructor(private val stocksDetailsApi: StocksDetailsApi, private val companyDao: CompanyDao, private val utils: Utils) {

    fun getCompany(stockSymbol: String): Observable<Company> {
        val hasConnection = utils.isConnectedToInternet()
        var observableFromApi: Observable<Company>? = null
        if (hasConnection) {
            observableFromApi = getCompanyDetailsFromApi(stockSymbol)
        }
        val observableFromDB = getCompanyDetailsFromDb(stockSymbol)

        return if (hasConnection) Observable.concatArrayEager(observableFromApi, observableFromDB) else observableFromDB
    }

    private fun getCompanyDetailsFromApi(stockSymbol: String): Observable<Company> {
        return stocksDetailsApi.getStockDetails(stockSymbol)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map({ response -> parseCompanyData(response, stockSymbol) })
                .doOnNext { response ->
                    response?.let {
                        companyDao.insertCompany(response)
                    }
                }
    }

    private fun getCompanyDetailsFromDb(stockSymbol: String): Observable<Company> {
        return companyDao.getCompany(stockSymbol)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .toObservable()
    }

    private fun parseCompanyData(response: CompanyDetailsResponse, stockSymbol: String): Company {
        val chart = response.chartItems
        val id = stockSymbol + "_iex"
        val company = response.company
        company.id = id
        company.chart = chart
        return company
    }
}