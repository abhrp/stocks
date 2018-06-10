package com.github.abhrp.stocksdemo.network.config

import android.content.Context
import com.github.abhrp.stocksdemo.application.ApplicationContext
import com.github.abhrp.stocksdemo.application.ApplicationContextModule
import com.github.abhrp.stocksdemo.application.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit


@Module(includes = [ApplicationContextModule::class])
class OkHttpClientModule {

    @Provides
    fun okHttpClient(loggingInterceptor: HttpLoggingInterceptor, interceptor: Interceptor, cache: Cache): OkHttpClient = OkHttpClient.Builder().connectTimeout(NetworkConstants.CONNECT_TIMEOUT, TimeUnit.SECONDS).readTimeout(NetworkConstants.READ_TIMEOUT, TimeUnit.SECONDS).addInterceptor(loggingInterceptor).addInterceptor(interceptor).cache(cache).build()


    @Provides
    fun cache(file: File): Cache = Cache(file, NetworkConstants.CACHE_SIZE)


    @Provides
    @ApplicationScope
    fun file(@ApplicationContext context: Context): File {
        val file = File(context.cacheDir, NetworkConstants.HTTP_CACHE)
        file.mkdirs()
        return file
    }

    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
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
        headers.set("Accept", NetworkConstants.ACCEPT)
        return headers.build()
    }
}