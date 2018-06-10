package com.github.abhrp.stocksdemo.application

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ApplicationContextModule(private var app: Application) {

    @ApplicationContext
    @ApplicationScope
    @Provides
    fun getContext(): Context = app.applicationContext

}