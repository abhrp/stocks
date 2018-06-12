package com.github.abhrp.stocksdemo.di.modules

import android.app.Application
import com.github.abhrp.stocksdemo.util.OHCLDataParser
import com.github.abhrp.stocksdemo.util.Utils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [(ApplicationModule::class)])
class UtilsModule {
    @Provides
    @Singleton
    fun provideUtils(application: Application) = Utils(application)

    @Provides
    @Singleton
    fun ohclDataParser() = OHCLDataParser()
}