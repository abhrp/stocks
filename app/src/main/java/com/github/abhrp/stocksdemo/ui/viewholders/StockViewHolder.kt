package com.github.abhrp.stocksdemo.ui.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.github.abhrp.stocksdemo.R
import com.github.abhrp.stocksdemo.data.model.Stock
import com.github.abhrp.stocksdemo.ui.listeners.StockItemClickListener
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_stock.*


class StockViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun bind(stock: Stock, isUp: Boolean, stockItemClickListener: StockItemClickListener?) {
        company_logo.setImageUrl(stock.logo, R.drawable.ic_placeholder)
        company_name.text = stock.company
        stock_symbol.text = stock.symbol
        stock_price.text = stock.price.toString()
        stock_exchange.text = stock.exchange
        stock_change_price.text = stock.change.toString()
        stock_change_percentage.text = containerView.context.getString(R.string.stock_change_percentage, stock.changePercent.toString())
        if (isUp) {
            stock_trend_icon.setImageResource(R.drawable.ic_price_up)
            stock_price.setTextColor(containerView.context.resources.getColor(R.color.price_up))
        } else {
            stock_trend_icon.setImageResource(R.drawable.ic_price_down)
            stock_price.setTextColor(containerView.context.resources.getColor(R.color.price_down))
        }
        item_stock_container.setOnClickListener {
            stockItemClickListener?.stockItemClicked(stock)
        }
    }
}