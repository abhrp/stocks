package com.github.abhrp.stocksdemo.di.modules

import android.app.Application
import com.github.abhrp.stocksdemo.application.AppConstants
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module(includes = [ApplicationModule::class])
class OkHttpClientModule {

    @Provides
    fun okHttpClient(loggingInterceptor: HttpLoggingInterceptor, interceptor: Interceptor, cache: Cache): OkHttpClient = OkHttpClient.Builder().connectTimeout(AppConstants.CONNECT_TIMEOUT, TimeUnit.SECONDS).readTimeout(AppConstants.READ_TIMEOUT, TimeUnit.SECONDS).addInterceptor(loggingInterceptor).addInterceptor(interceptor).cache(cache).build()


    @Provides
    fun cache(file: File): Cache = Cache(file, AppConstants.CACHE_SIZE)


    @Provides
    @Singleton
    fun file(app: Application): File {
        val file = File(app.cacheDir, AppConstants.HTTP_CACHE)
        file.mkdirs()
        return file
    }

    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides
    fun interceptor(headers: Headers): Interceptor = Interceptor { chain ->
        val request = chain.request()?.newBuilder()?.headers(headers)?.build()
        chain.proceed(request!!)
    }


    @Provides
    fun headers(): Headers {
        val headers: Headers.Builder = Headers.Builder()
        headers.set("Accept", AppConstants.ACCEPT)
        return headers.build()
    }
}