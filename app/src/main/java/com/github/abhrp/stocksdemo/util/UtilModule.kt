package com.github.abhrp.stocksdemo.util

import android.app.Application
import com.github.abhrp.stocksdemo.application.ApplicationModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [(ApplicationModule::class)])
class UtilModule {
    @Provides
    @Singleton
    fun provideUtils(application: Application) = Utils(application)
}