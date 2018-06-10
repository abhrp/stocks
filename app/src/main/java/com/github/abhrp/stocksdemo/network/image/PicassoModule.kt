package com.github.abhrp.stocksdemo.network.image

import android.content.Context
import com.github.abhrp.stocksdemo.application.ApplicationContext
import com.github.abhrp.stocksdemo.application.ApplicationScope
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides

@Module
class PicassoModule {

    @ApplicationScope
    @Provides
    fun picasso(@ApplicationContext context: Context, okHttp3Downloader: OkHttp3Downloader) = Picasso.Builder(context).downloader(okHttp3Downloader).build()

    @Provides
    fun okHttp3Downloader(@ApplicationContext context: Context): OkHttp3Downloader = OkHttp3Downloader(context, Long.MAX_VALUE)
}