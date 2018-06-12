package com.github.abhrp.stocksdemo.util

import android.text.TextUtils
import com.github.abhrp.stocksdemo.BuildConfig
import timber.log.Timber

class Logger {
    companion object {
        private val TAG = Logger::class.qualifiedName

        fun i(tag: String?, message: String) {
            if (BuildConfig.DEBUG) {
                val t = if (TextUtils.isEmpty(tag)) TAG else tag
                Timber.tag(t).i(message)
            }
        }

        fun e(tag: String?, throwable: Throwable) {
            if (BuildConfig.DEBUG) {
                val t = if (TextUtils.isEmpty(tag)) TAG else tag
                Timber.tag(t).e(throwable)
            }
        }
    }
}