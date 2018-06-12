package com.github.abhrp.stocksdemo.viemodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.github.abhrp.stocksdemo.data.model.Stock
import com.github.abhrp.stocksdemo.data.repositories.StocksRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class StocksViewModel @Inject constructor(private val repository: StocksRepository) : ViewModel() {
    private var stocksListResult: MutableLiveData<List<Stock>> = MutableLiveData()
    private var stocksListError: MutableLiveData<Throwable> = MutableLiveData()
    private var stocksListLoader: MutableLiveData<Boolean> = MutableLiveData()
    private lateinit var disposableObserver: DisposableObserver<List<Stock>>

    fun getStocks(): LiveData<List<Stock>> {
        return stocksListResult
    }

    fun getStocksError(): LiveData<Throwable> {
        return stocksListError
    }

    fun getStocksLoader(): LiveData<Boolean> {
        return stocksListLoader
    }

    fun loadStocks() {
        if (::disposableObserver.isInitialized && !disposableObserver.isDisposed) {
            disposableObserver.dispose()
        }

        disposableObserver = object : DisposableObserver<List<Stock>>() {
            override fun onComplete() {

            }

            override fun onNext(stocks: List<Stock>) {
                stocksListResult.postValue(stocks)
                stocksListLoader.postValue(false)
            }

            override fun onError(e: Throwable) {
                stocksListError.postValue(e)
                stocksListLoader.postValue(false)
            }
        }
        repository.getStocks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableObserver)
    }

    fun clearDisposables() {
        if (::disposableObserver.isInitialized && !disposableObserver.isDisposed) disposableObserver.dispose()
    }
}