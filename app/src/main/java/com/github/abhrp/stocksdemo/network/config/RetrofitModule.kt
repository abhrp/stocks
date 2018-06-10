package com.github.abhrp.stocksdemo.network.config

import com.github.abhrp.stocksdemo.application.ApplicationScope
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [OkHttpClientModule::class])
class RetrofitModule {

    @ApplicationScope
    @Provides
    fun retrofit(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory) = Retrofit.Builder().baseUrl(NetworkConstants.BASE_URL).addConverterFactory(gsonConverterFactory).client(okHttpClient).build()

    @Provides
    fun gson(): Gson = GsonBuilder().create()

    @Provides
    fun gsonConvertorFactory(gson: Gson):GsonConverterFactory = GsonConverterFactory.create(gson)
}