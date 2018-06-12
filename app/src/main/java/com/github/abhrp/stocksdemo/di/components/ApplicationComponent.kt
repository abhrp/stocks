package com.github.abhrp.stocksdemo.di.components

import com.github.abhrp.stocksdemo.application.StocksApplication
import com.github.abhrp.stocksdemo.data.local.daos.CompanyDao
import com.github.abhrp.stocksdemo.data.local.daos.StockDao
import com.github.abhrp.stocksdemo.data.remote.StocksDetailsApi
import com.github.abhrp.stocksdemo.data.remote.StocksListingApi
import com.github.abhrp.stocksdemo.di.modules.*
import com.squareup.picasso.Picasso
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidInjectionModule::class),
    (ApplicationModule::class),
    (ActivityInjectionModule::class),
    (ApiProviderModule::class),
    (ImageModule::class),
    (DatabaseModule::class),
    (UtilsModule::class),
    (ViewModelFactoryModule::class)])
interface ApplicationComponent {
    fun inject(app: StocksApplication)
    fun picasso(): Picasso
    fun stockApi(): StocksListingApi
    fun companyApi(): StocksDetailsApi
    fun stockDao(): StockDao
    fun companyDao(): CompanyDao
}