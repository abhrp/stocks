package com.github.abhrp.stocksdemo.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.abhrp.stocksdemo.R
import com.github.abhrp.stocksdemo.data.model.Stock
import com.github.abhrp.stocksdemo.ui.listeners.StockItemClickListener
import com.github.abhrp.stocksdemo.ui.viewholders.StockViewHolder

class StocksAdapter : RecyclerView.Adapter<StockViewHolder>() {

    private lateinit var stocks: List<Stock>
    private var stockItemClickListener: StockItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_stock,
                parent, false)
        return StockViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return if (::stocks.isInitialized) stocks.size else 0
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        if (::stocks.isInitialized && position >= 0 && position < stocks.size) {
            val stock = stocks[position]
            val open = stock.open
            val close = stock.close
            holder.bind(stock, open <= close, stockItemClickListener)
        }
    }

    fun setStockItemClickListener(stockItemClickListener: StockItemClickListener?) {
        this.stockItemClickListener = stockItemClickListener
    }

    fun setList(stocks: List<Stock>) {
        this.stocks = stocks
        notifyDataSetChanged()
    }
}