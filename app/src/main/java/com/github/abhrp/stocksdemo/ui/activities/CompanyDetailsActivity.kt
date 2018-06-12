package com.github.abhrp.stocksdemo.ui.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.github.abhrp.stocksdemo.R
import com.github.abhrp.stocksdemo.application.AppConstants
import com.github.abhrp.stocksdemo.data.model.Company
import com.github.abhrp.stocksdemo.data.model.Stock
import com.github.abhrp.stocksdemo.util.Logger
import com.github.abhrp.stocksdemo.util.Utils
import com.github.abhrp.stocksdemo.viemodels.CompanyDetailsViewModel
import com.github.abhrp.stocksdemo.viemodels.factories.CompanyViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_company_details.*
import kotlinx.android.synthetic.main.content_company_details.*
import kotlinx.android.synthetic.main.notification_template_lines_media.view.*
import javax.inject.Inject

class CompanyDetailsActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = CompanyDetailsActivity::class.java.canonicalName

    private lateinit var stock: Stock

    @Inject
    lateinit var companyViewModelFactory: CompanyViewModelFactory
    @Inject
    lateinit var utils: Utils

    private lateinit var companyViewModel: CompanyDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_company_details)
        setUpIntentData()
        setUpViewModel()
        setUpActionBar()
        setUpStockDetails()
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
                updateUI(it)
            }
        })
        companyViewModel.getCompanyError().observe(this, Observer<Throwable> { error ->
            error?.let {
                Logger.e(TAG, error)
            }
        })

        companyViewModel.getCompanyLoader().observe(this, Observer<Boolean> {

        })
    }

    private fun loadCompany() {
        companyViewModel.loadStocks(stock.symbol)
    }

    private fun updateUI(company: Company) {
        runOnUiThread {
            setUpCompanyDetails(company)
        }
    }

    private fun setUpCompanyDetails(company: Company) {
        company_description.text = company.description
        industry.text = company.industry
        sector.text = company.sector
        ceo.text = company.ceo
        website.text = company.website
        website.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.website) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(v.text.toString()))
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
