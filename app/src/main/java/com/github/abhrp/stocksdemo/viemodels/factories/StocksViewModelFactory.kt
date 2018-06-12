package com.github.abhrp.stocksdemo.viemodels.factories

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.github.abhrp.stocksdemo.application.AppConstants
import com.github.abhrp.stocksdemo.viemodels.StocksViewModel
import javax.inject.Inject

class StocksViewModelFactory @Inject constructor(private val stocksViewModel: StocksViewModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StocksViewModel::class.java)) {
            return stocksViewModel as T
        }
        throw IllegalArgumentException(AppConstants.UNKNOWN_ERROR)
    }
}