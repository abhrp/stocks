package com.github.abhrp.stocksdemo.application

object AppConstants {
    const val BASE_URL = "https://api.iextrading.com/1.0/"
    const val BASE_IMAGE_URL = "https://storage.googleapis.com/iex/api/logos/"
    const val IMAGE_TYPE = ".png"
    const val ACCEPT = "application/json"
    const val HTTP_CACHE = "HttpCache";
    const val CACHE_SIZE: Long = 10 * 1000 * 1000
    const val CONNECT_TIMEOUT = 60L
    const val READ_TIMEOUT = 60L
    const val UNKNOWN_ERROR = "Unknown error"
    const val STOCK = "stock"
}