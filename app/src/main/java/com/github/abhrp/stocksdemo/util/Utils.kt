package com.github.abhrp.stocksdemo.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.github.abhrp.stocksdemo.application.AppConstants
import com.github.abhrp.stocksdemo.data.model.Stock
import java.text.DecimalFormat
import javax.inject.Inject

class Utils @Inject constructor(private val context: Context) {

    fun isConnectedToInternet(): Boolean {
        val connectivity = context.getSystemService(
                Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        if (connectivity != null) {
            val info = connectivity.allNetworkInfo
            if (info != null)
                for (i in info.indices)
                    if (info[i].state == NetworkInfo.State.CONNECTED) {
                        return true
                    }
        }
        return false
    }


    fun getImageLogoUrl(symbol: String?): String? {
        symbol?.let {
            return AppConstants.BASE_IMAGE_URL + symbol + AppConstants.IMAGE_TYPE
        }
        return null
    }

    fun isPriceUp(stock: Stock): Boolean {
        return stock.open <= stock.close
    }

    fun priceChange(stock: Stock): Double {
        val change = if (stock.change != 0.0) stock.change else decimalFormattedNumber(stock.close - stock.open)
        return Math.abs(change)
    }

    fun changePercentage(stock: Stock): Double {
        val changePercentage = if (stock.changePercent != 0.0) stock.changePercent else decimalFormattedNumber((((stock.close - stock.open) / stock.open) * 100))
        return Math.abs(changePercentage)
    }

    fun decimalFormattedNumber(number: Double): Double {
        return DecimalFormat(".###").format(number).toDouble()
    }
}