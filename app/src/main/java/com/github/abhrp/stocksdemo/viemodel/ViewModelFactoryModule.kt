package com.github.abhrp.stocksdemo.viemodel

import android.arch.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelFactoryModule {
    @Provides
    @Singleton
    fun provideStockViewModelFactory(factory: StocksViewModelFactory): ViewModelProvider.Factory = factory

    @Provides
    @Singleton
    fun provideCompanyViewModelFactory(factory: CompanyViewModelFactory): ViewModelProvider.Factory = factory
}