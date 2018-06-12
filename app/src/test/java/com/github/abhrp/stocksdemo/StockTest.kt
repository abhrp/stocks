package com.github.abhrp.stocksdemo

import com.github.abhrp.stocksdemo.data.model.Stock
import com.github.abhrp.stocksdemo.data.remote.StocksListingApi
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class StockTest {

    @Before
    fun setUp() {

    }

    @Test
    fun testSomething() {
        val server = Mockito.mock(StocksListingApi::class.java)
        `when`(server.getMostActiveStocks()).then{
            arrayListOf(
                    Stock("AAPL", null, "Apple", 0.23, 0.003, 2134.0, "NYSE"),
                    Stock("MSFT", null, "Microsoft", 0.45, 0.013, 477.0, "NYSE")
            )}

    }
}