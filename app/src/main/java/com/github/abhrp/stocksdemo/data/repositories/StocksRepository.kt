package com.github.abhrp.stocksdemo.data.repositories

import com.github.abhrp.stocksdemo.data.local.daos.StockDao
import com.github.abhrp.stocksdemo.data.model.Stock
import com.github.abhrp.stocksdemo.data.remote.StocksListingApi
import com.github.abhrp.stocksdemo.util.Utils
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class StocksRepository @Inject constructor(private val stocksListingApi: StocksListingApi, private val stockDao: StockDao, private val utils: Utils) {

    fun getStocks(): Observable<List<Stock>> {
        val hasConnection = utils.isConnectedToInternet()
        var observableFromApi: Observable<List<Stock>>? = null
        if (hasConnection) {
            observableFromApi = Observable.concatArrayEager(getMostActiveStocks(), getGainers(), getLosers())
        }
        val observableFromDB = getStocksFromDb()

        return if (hasConnection) Observable.concatArrayEager(observableFromApi, observableFromDB) else observableFromDB
    }

    private fun getMostActiveStocks(): Observable<List<Stock>> {
        return stocksListingApi.getMostActiveStocks()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .doOnNext { parseStockList(it) }
    }

    private fun parseStockList(stocks: List<Stock>) {
        for (stock in stocks) {
            stock.logo = utils.getImageLogoUrl(stock.symbol)
            stock.isUp = utils.isPriceUp(stock)
            stock.change = utils.priceChange(stock)
            stock.changePercent = utils.changePercentage(stock)
            stockDao.insertStock(stock)
        }
    }

    private fun getGainers(): Observable<List<Stock>> {
        return stocksListingApi.getGainers()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .doOnNext { parseStockList(it) }
    }

    private fun getLosers(): Observable<List<Stock>> {
        return stocksListingApi.getLosers()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .doOnNext { parseStockList(it) }
    }

    private fun getStocksFromDb(): Observable<List<Stock>> {
        return stockDao.getStocks()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .toObservable()

    }
}