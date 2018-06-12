package com.github.abhrp.stocksdemo.ui

import com.github.abhrp.stocksdemo.data.model.Stock

interface StockItemClickListener {
    fun stockItemClicked(stock: Stock)
}