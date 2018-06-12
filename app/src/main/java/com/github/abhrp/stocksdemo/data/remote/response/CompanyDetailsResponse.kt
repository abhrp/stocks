package com.github.abhrp.stocksdemo.data.remote.response

import com.github.abhrp.stocksdemo.data.model.ChartItem
import com.github.abhrp.stocksdemo.data.model.Company
import com.github.abhrp.stocksdemo.data.model.Stock
import com.google.gson.annotations.SerializedName

data class CompanyDetailsResponse(
        @SerializedName("quote") val stock: Stock,
        @SerializedName("company") val company: Company,
        @SerializedName("chart") val chartItems: List<ChartItem>
)