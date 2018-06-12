package com.github.abhrp.stocksdemo.application

import com.github.abhrp.stocksdemo.ui.activities.CompanyDetailsActivity
import com.github.abhrp.stocksdemo.ui.activities.StocksActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityInjectionModule {
    @ContributesAndroidInjector
    abstract fun contributesStockActivity(): StocksActivity

    @ContributesAndroidInjector
    abstract fun contributesDetailsActivity(): CompanyDetailsActivity
}