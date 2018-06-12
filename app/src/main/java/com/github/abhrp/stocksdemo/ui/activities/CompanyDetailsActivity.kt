package com.github.abhrp.stocksdemo.ui.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.github.abhrp.stocksdemo.R
import com.github.abhrp.stocksdemo.application.AppConstants
import com.github.abhrp.stocksdemo.data.model.Company
import com.github.abhrp.stocksdemo.data.model.Stock
import com.github.abhrp.stocksdemo.util.Logger
import com.github.abhrp.stocksdemo.util.OHCLDataParser
import com.github.abhrp.stocksdemo.util.Utils
import com.github.abhrp.stocksdemo.viemodels.CompanyDetailsViewModel
import com.github.abhrp.stocksdemo.viemodels.factories.CompanyViewModelFactory
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.CandleData
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_company_details.*
import kotlinx.android.synthetic.main.content_company_details.*
import javax.inject.Inject

class CompanyDetailsActivity : AppCompatActivity() {

    private val TAG = CompanyDetailsActivity::class.java.canonicalName

    private lateinit var stock: Stock

    @Inject
    lateinit var companyViewModelFactory: CompanyViewModelFactory
    @Inject
    lateinit var utils: Utils
    @Inject
    lateinit var ohclDataParser: OHCLDataParser

    private lateinit var companyViewModel: CompanyDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_company_details)
        setUpIntentData()
        setUpViewModel()
        setUpActionBar()
        setUpStockDetails()
        setUpChart()
        handleCompanyData()
        loadCompany()
    }

    private fun setUpIntentData() {
        stock = intent.extras[AppConstants.STOCK] as Stock
    }

    private fun setUpViewModel() {
        companyViewModel = ViewModelProviders.of(this, companyViewModelFactory).get(CompanyDetailsViewModel::class.java)
    }

    private fun setUpActionBar() {
        setSupportActionBar(toolbar_details)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.subtitle = getString(R.string.stock_symbol, stock.symbol)
        supportActionBar?.title = stock.company
    }

    private fun setUpStockDetails() {
        company_logo.setImageUrl(stock.logo, R.drawable.ic_placeholder)
        stock_symbol.text = stock.symbol
        stock_low.text = stock.low.toString()
        stock_high.text = stock.high.toString()
        stock_price.text = stock.price.toString()
        if (stock.isUp) {
            stock_price.setTextColor(resources.getColor(R.color.price_up))
            stock_trend_icon.setImageResource(R.drawable.ic_price_up)
        } else {
            stock_price.setTextColor(resources.getColor(R.color.price_down))
            stock_trend_icon.setImageResource(R.drawable.ic_price_down)
        }
    }

    private fun handleCompanyData() {
        companyViewModel.getCompany().observe(this, Observer<Company> { company ->
            company?.let {
                var candleData = CandleData()
                company.chart?.let {
                    candleData = ohclDataParser.createChartData(it)
                }
                updateUI(it, candleData)
            }
        })
        companyViewModel.getCompanyError().observe(this, Observer<Throwable> { error ->
            error?.let {
                Snackbar.make(company_details, getString(R.string.error), Snackbar.LENGTH_LONG).show()
                Logger.e(TAG, error)
            }
        })
    }

    private fun loadCompany() {
        companyViewModel.loadStocks(stock.symbol)
    }

    private fun updateUI(company: Company, candleData: CandleData) {
        runOnUiThread {
            setUpCompanyDetails(company)
            loadChart(candleData)
            if (!utils.isConnectedToInternet()) {
                Snackbar.make(company_details, getString(R.string.offline_message), Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun setUpCompanyDetails(company: Company) {
        company_description.text = company.description
        industry.text = company.industry
        sector.text = company.sector
        ceo.text = company.ceo
        website.text = company.website
        website.setOnClickListener{goToWebsite(company.website)}
    }

    private fun setUpChart() {
        chart_view.description.isEnabled = false
        chart_view.setMaxVisibleValueCount(40)
        chart_view.setPinchZoom(false)
        chart_view.setDrawGridBackground(false)
        val xAxis = chart_view.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        val leftAxis = chart_view.axisLeft
        leftAxis.setDrawGridLines(false)
        leftAxis.setDrawAxisLine(false)
        val rightAxis = chart_view.axisRight
        rightAxis.isEnabled = false
        chart_view.legend.isEnabled = false
        chart_view.resetTracking()
    }

    private fun loadChart(candleData: CandleData) {
        chart_view.data = candleData
        chart_view.invalidate()
    }

    private fun goToWebsite(url: String?) {
        url?.let {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(it)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        companyViewModel.clearDisposables()
    }
}
