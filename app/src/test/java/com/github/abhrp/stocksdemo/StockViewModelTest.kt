package com.github.abhrp.stocksdemo

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.github.abhrp.stocksdemo.data.model.Stock
import com.github.abhrp.stocksdemo.data.repositories.StocksRepository
import com.github.abhrp.stocksdemo.viemodels.StocksViewModel
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class StockViewModelTest {

    @Mock
    private lateinit var stocksRepository:StocksRepository

    private lateinit var stocksViewModel: StocksViewModel

    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        stocksViewModel = StocksViewModel(stocksRepository)

        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @After
    fun tearDown() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }

    @Test
    fun testForEmpty() {
        Mockito.`when`(stocksRepository.getStocks()).thenReturn(Observable.just(ArrayList<Stock>()))
        val observer = mock<Observer<List<Stock>>>()
        stocksViewModel.loadStocks()
        stocksViewModel.getStocks().observeForever(observer)
        verify(observer).onChanged(ArrayList<Stock>())
    }

    @Test
    fun testForSomeValue() {
        val list = listOf(Stock("APPL", "https://storage.googleapis.com/iex/api/logos/APPL.png", "Apple computers", 0.45, 0.87, 1435.0, "NYSE", 1500.0, 1433.0, 1440.0, 1435.0, true))
        Mockito.`when`(stocksRepository.getStocks()).thenReturn(Observable.just(list))
        val observer = mock<Observer<List<Stock>>>()
        stocksViewModel.loadStocks()
        stocksViewModel.getStocks().observeForever(observer)
        verify(observer).onChanged(list)
    }

    @Test
    fun testForSymbolValue() {
        val list = listOf(Stock("APPL", "https://storage.googleapis.com/iex/api/logos/APPL.png", "Apple computers", 0.45, 0.87, 1435.0, "NYSE", 1500.0, 1433.0, 1440.0, 1435.0, true))
        Mockito.`when`(stocksRepository.getStocks()).thenReturn(Observable.just(list))
        stocksViewModel.loadStocks()
        val liveData = stocksViewModel.getStocks().value
        val symbol = liveData?.get(0)?.symbol as String
        assert(symbol.contentEquals(list[0].symbol))
    }

    @Test
    fun testForImageUrl() {
        val list = listOf(Stock("APPL", "https://storage.googleapis.com/iex/api/logos/APPL.png", "Apple computers", 0.45, 0.87, 1435.0, "NYSE", 1500.0, 1433.0, 1440.0, 1435.0, true))
        Mockito.`when`(stocksRepository.getStocks()).thenReturn(Observable.just(list))
        stocksViewModel.loadStocks()
        val liveData = stocksViewModel.getStocks().value
        val logo = liveData?.get(0)?.logo as String
        assert(logo.contentEquals("https://storage.googleapis.com/iex/api/logos/APPL.png"))
    }

    @Test
    fun testListSizeStock() {
        val list = listOf(Stock("APPL", "https://storage.googleapis.com/iex/api/logos/APPL.png", "Apple computers", 0.45, 0.87, 1435.0, "NYSE", 1500.0, 1433.0, 1440.0, 1435.0, true))
        Mockito.`when`(stocksRepository.getStocks()).thenReturn(Observable.just(list))
        stocksViewModel.loadStocks()
        val liveData = stocksViewModel.getStocks().value
        assert(liveData?.size == list.size)
    }

    @Test
    fun testError() {
        val list = listOf(Stock("APPL", "https://storage.googleapis.com/iex/api/logos/APPL.png", "Apple computers", 0.45, 0.87, 1435.0, "NYSE", 1500.0, 1433.0, 1440.0, 1435.0, true))
        Mockito.`when`(stocksRepository.getStocks()).thenReturn(Observable.just(list))
        stocksViewModel.loadStocks()
        val liveData = stocksViewModel.getStocksError().value
        liveData?.let {
            assert(it.equals(null))
        }
    }

    @Test
    fun testLoader() {
        val list = listOf(Stock("APPL", "https://storage.googleapis.com/iex/api/logos/APPL.png", "Apple computers", 0.45, 0.87, 1435.0, "NYSE", 1500.0, 1433.0, 1440.0, 1435.0, true))
        Mockito.`when`(stocksRepository.getStocks()).thenReturn(Observable.just(list))
        stocksViewModel.loadStocks()
        val liveData = stocksViewModel.getStocksLoader().value
        liveData?.let {
            assert(!it)
        }
    }
}

