package com.github.abhrp.stocksdemo.application

import com.github.abhrp.stocksdemo.data.local.CompanyDao
import com.github.abhrp.stocksdemo.data.local.DatabaseModule
import com.github.abhrp.stocksdemo.data.local.StockDao
import com.github.abhrp.stocksdemo.data.remote.ApiProviderModule
import com.github.abhrp.stocksdemo.data.remote.StocksDetailsApi
import com.github.abhrp.stocksdemo.data.remote.StocksListingApi
import com.github.abhrp.stocksdemo.network.image.PicassoModule
import com.github.abhrp.stocksdemo.util.UtilModule
import com.github.abhrp.stocksdemo.viemodel.ViewModelFactoryModule
import com.squareup.picasso.Picasso
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidInjectionModule::class),
   (ApplicationModule::class),
   (ActivityInjectionModule::class),
   (ApiProviderModule::class),
   (PicassoModule::class),
   (DatabaseModule::class),
   (UtilModule::class),
   (ViewModelFactoryModule::class)])
interface ApplicationComponent {
   fun inject(app: StocksApplication)
   fun picasso(): Picasso
   fun stockApi(): StocksListingApi
   fun companyApi(): StocksDetailsApi
   fun stockDao(): StockDao
   fun companyDao(): CompanyDao
}