package com.github.abhrp.stocksdemo.application

import com.github.abhrp.stocksdemo.db.DatabaseModule
import com.github.abhrp.stocksdemo.db.StockDao
import com.github.abhrp.stocksdemo.network.config.RetrofitModule
import com.github.abhrp.stocksdemo.network.image.PicassoModule
import com.squareup.picasso.Picasso
import dagger.Component
import retrofit2.Retrofit

@ApplicationScope
@Component(modules = [(ApplicationContextModule::class), (RetrofitModule::class), (PicassoModule::class), (DatabaseModule::class)])
interface ApplicationComponent {
    fun retrofit(): Retrofit

    fun picasso(): Picasso

    fun stockDao(): StockDao
}