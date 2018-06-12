package com.github.abhrp.stocksdemo.di.modules

import android.arch.lifecycle.ViewModelProvider
import com.github.abhrp.stocksdemo.viemodels.factories.CompanyViewModelFactory
import com.github.abhrp.stocksdemo.viemodels.factories.StocksViewModelFactory
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