package com.github.abhrp.stocksdemo.network.response

import com.github.abhrp.stocksdemo.model.ChartItem
import com.github.abhrp.stocksdemo.model.Company
import com.github.abhrp.stocksdemo.model.Stock
import com.google.gson.annotations.SerializedName

data class CompanyDetailsResponse(
        @SerializedName("quote") val stock: Stock?,
        @SerializedName("company") val company: Company?,
        @SerializedName("chart") val chartItems: List<ChartItem>?
)