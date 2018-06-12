package com.github.abhrp.stocksdemo.viemodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.github.abhrp.stocksdemo.data.model.Company
import com.github.abhrp.stocksdemo.data.repositories.CompanyDetailsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CompanyDetailsViewModel @Inject constructor(private val repository: CompanyDetailsRepository) : ViewModel() {
    private var companyResult: MutableLiveData<Company> = MutableLiveData()
    private var companyError: MutableLiveData<Throwable> = MutableLiveData()
    private lateinit var disposableObserver: DisposableObserver<Company>

    fun getCompany(): LiveData<Company> {
        return companyResult
    }

    fun getCompanyError(): LiveData<Throwable> {
        return companyError
    }

    fun loadStocks(stockSymbol: String) {
        if (::disposableObserver.isInitialized && !disposableObserver.isDisposed) {
            disposableObserver.dispose()
        }

        disposableObserver = object : DisposableObserver<Company>() {
            override fun onComplete() {

            }

            override fun onNext(company: Company) {
                companyResult.postValue(company)
            }

            override fun onError(e: Throwable) {
                companyError.postValue(e)
            }
        }
        repository.getCompany(stockSymbol)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableObserver)
    }

    fun clearDisposables() {
        if (::disposableObserver.isInitialized && !disposableObserver.isDisposed) disposableObserver.dispose()
    }
}