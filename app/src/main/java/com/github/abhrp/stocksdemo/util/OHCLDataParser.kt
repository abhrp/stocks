package com.github.abhrp.stocksdemo.util

import android.graphics.Color
import android.graphics.Paint
import com.github.abhrp.stocksdemo.data.model.ChartItem
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CandleDataSet
import com.github.mikephil.charting.data.CandleEntry
import javax.inject.Inject

class OHCLDataParser @Inject constructor() {

    fun createChartData(chart: List<ChartItem>): CandleData {
        val candleEntries = getCandleEntries(chart)
        val candleDataSet = getCandleDataSet(candleEntries)
        return CandleData(candleDataSet)
    }

    private fun getCandleDataSet(candleEntries: List<CandleEntry>): CandleDataSet {
        val set = CandleDataSet(candleEntries, "Data set")
        set.setDrawIcons(false)
        set.axisDependency = YAxis.AxisDependency.LEFT
        set.shadowWidth = 0.7f
        set.shadowColor = Color.DKGRAY
        set.decreasingColor = Color.RED
        set.decreasingPaintStyle = Paint.Style.FILL
        set.increasingColor = Color.rgb(122, 242, 84)
        set.increasingPaintStyle = Paint.Style.STROKE
        set.neutralColor = Color.BLUE
        return set
    }

    private fun getCandleEntries(chart: List<ChartItem>): List<CandleEntry> {
        val candleEntries = ArrayList<CandleEntry>()
        chart.forEachIndexed { index, chartItem ->
            candleEntries.add(getCandleEntry(chartItem, index.toFloat()))
        }
        return candleEntries
    }

    private fun getCandleEntry(chartItem: ChartItem, index: Float): CandleEntry {
        return CandleEntry(index, chartItem.high.toFloat(), chartItem.low.toFloat(), chartItem.open.toFloat(), chartItem.close.toFloat())
    }
}