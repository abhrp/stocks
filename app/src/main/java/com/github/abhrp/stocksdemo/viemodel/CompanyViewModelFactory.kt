package com.github.abhrp.stocksdemo.viemodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.github.abhrp.stocksdemo.application.AppConstants
import javax.inject.Inject

class CompanyViewModelFactory @Inject constructor(private val companyViewModel: CompanyDetailsViewModel): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StocksViewModel::class.java!!)) {
            return companyViewModel as T
        }
        throw IllegalArgumentException(AppConstants.UNKNOWN_ERROR)
    }
}