package com.github.abhrp.stocksdemo.application

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.github.abhrp.stocksdemo.BuildConfig
import com.github.abhrp.stocksdemo.db.DatabaseModule
import timber.log.Timber

class StocksApplication: Application() {

    init {
        instance = this
    }

    companion object {
        private var  instance:StocksApplication? = null

        fun getApplication(): StocksApplication {
            return instance!!
        }
    }

    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        applicationComponent = DaggerApplicationComponent.builder().applicationContextModule(ApplicationContextModule(this)).databaseModule(DatabaseModule(this)).build()
    }

    fun getApplicationComponent(): ApplicationComponent {
        return applicationComponent;
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}