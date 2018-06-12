package com.github.abhrp.stocksdemo.util

import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


@Module
class SchedulerProviderModule {
    @Provides
    fun schedulerProvider(): SchedulerProvider = SchedulerProvider(Schedulers.io(), AndroidSchedulers.mainThread())
}