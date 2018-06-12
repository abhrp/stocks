package com.github.abhrp.stocksdemo.application

import android.app.Activity
import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.github.abhrp.stocksdemo.BuildConfig
import com.github.abhrp.stocksdemo.di.components.ApplicationComponent
import com.github.abhrp.stocksdemo.di.components.DaggerApplicationComponent
import com.github.abhrp.stocksdemo.di.modules.ApplicationModule
import com.github.abhrp.stocksdemo.di.modules.NetworkModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class StocksApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>


    init {
        instance = this
    }

    companion object {
        private var instance: StocksApplication? = null

        fun getApplication(): StocksApplication {
            return instance!!
        }
    }

    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).networkModule(NetworkModule(AppConstants.BASE_URL)).build()
        applicationComponent.inject(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    fun getApplicationComponent(): ApplicationComponent {
        return applicationComponent;
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}