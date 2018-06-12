package com.github.abhrp.stocksdemo.ui.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.github.abhrp.stocksdemo.R
import com.github.abhrp.stocksdemo.application.AppConstants
import com.github.abhrp.stocksdemo.data.model.Stock
import com.github.abhrp.stocksdemo.ui.adapters.StocksAdapter
import com.github.abhrp.stocksdemo.ui.listeners.StockItemClickListener
import com.github.abhrp.stocksdemo.util.Logger
import com.github.abhrp.stocksdemo.util.Utils
import com.github.abhrp.stocksdemo.viemodels.StocksViewModel
import com.github.abhrp.stocksdemo.viemodels.factories.StocksViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_stocks.*
import javax.inject.Inject

class StocksActivity : AppCompatActivity(), StockItemClickListener {

    private val TAG = StocksActivity::class.java.canonicalName

    @Inject
    lateinit var stocksViewModelFactory: StocksViewModelFactory
    @Inject
    lateinit var utils: Utils

    private lateinit var stocksViewModel: StocksViewModel
    private lateinit var stocksAdapter: StocksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_stocks)
        setUpViewModel()
        setUpActionBar()
        setUpAdapter()
        setUpRecyclerView()
        setUpSwipeToRefresh()
        handleStockData()
        loadStocks()
    }

    private fun setUpViewModel() {
        stocksViewModel = ViewModelProviders.of(this, stocksViewModelFactory).get(StocksViewModel::class.java)
    }

    private fun setUpActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.title = getString(R.string.stocks_listing_title)
    }

    private fun setUpAdapter() {
        stocksAdapter = StocksAdapter()
        stocksAdapter.setStockItemClickListener(this)
    }

    private fun setUpRecyclerView() {
        val itemDecorator = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(resources.getDrawable(R.drawable.list_divider))
        stocks_list_view.addItemDecoration(itemDecorator)
        stocks_list_view.layoutManager = LinearLayoutManager(this)
        stocks_list_view.adapter = stocksAdapter
    }

    private fun setUpSwipeToRefresh() {
        swipe_to_refresh.setColorSchemeColors(resources.getColor(R.color.color_primary))
        swipe_to_refresh.setOnRefreshListener {
            loadStocks()
        }
    }

    private fun handleStockData() {
        stocksViewModel.getStocks().observe(this, Observer<List<Stock>> { stocks ->
            stocks?.let { updateUI(it) }
        })

        stocksViewModel.getStocksError().observe(this, Observer<Throwable> { error ->
            error?.let {
                Snackbar.make(swipe_to_refresh, getString(R.string.error), Snackbar.LENGTH_LONG).show()
                Logger.e(TAG, error)
            }
        })

        stocksViewModel.getStocksLoader().observe(this, Observer<Boolean> {
            swipe_to_refresh.isRefreshing = false
        })
    }

    private fun updateUI(stocks: List<Stock>) {
        runOnUiThread {
            stocksAdapter.setList(stocks)
            if (!utils.isConnectedToInternet()) {
                Snackbar.make(swipe_to_refresh, getString(R.string.offline_message), Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun loadStocks() {
        swipe_to_refresh.isRefreshing = true
        stocksViewModel.loadStocks()
    }

    override fun stockItemClicked(stock: Stock) {
        val intent = Intent(this, CompanyDetailsActivity::class.java)
        intent.putExtra(AppConstants.STOCK, stock)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_stocks, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_refresh) {
            loadStocks()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        stocksViewModel.clearDisposables()
        stocksAdapter.setStockItemClickListener(null)
    }
}
