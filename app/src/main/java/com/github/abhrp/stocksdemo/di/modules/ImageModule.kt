package com.github.abhrp.stocksdemo.di.modules

import android.app.Application
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [(ApplicationModule::class)])
class ImageModule {
    @Singleton
    @Provides
    fun picasso(app: Application, okHttp3Downloader: OkHttp3Downloader) = Picasso.Builder(app).downloader(okHttp3Downloader).build()

    @Provides
    fun okHttp3Downloader(app: Application): OkHttp3Downloader = OkHttp3Downloader(app, Long.MAX_VALUE)
}