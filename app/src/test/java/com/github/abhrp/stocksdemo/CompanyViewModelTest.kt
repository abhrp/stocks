package com.github.abhrp.stocksdemo

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.github.abhrp.stocksdemo.data.model.ChartItem
import com.github.abhrp.stocksdemo.data.model.Company
import com.github.abhrp.stocksdemo.data.repositories.CompanyDetailsRepository
import com.github.abhrp.stocksdemo.viemodels.CompanyDetailsViewModel
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

class CompanyViewModelTest {
    @Mock
    private lateinit var companyDetailsRepository: CompanyDetailsRepository

    private lateinit var companyDetailsViewModel: CompanyDetailsViewModel

    private lateinit var company_symbol: String

    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        companyDetailsViewModel = CompanyDetailsViewModel(companyDetailsRepository)
        company_symbol = "APPL"
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
    fun testForSomeValue() {
        val chart = listOf(
                ChartItem("2018-01-01", 23.0, 34.0, 2.0, 24.0, 2323232, 0.22, 0.32, "Jun 1", 12.0),
                ChartItem("2018-01-02", 24.0, 31.0, 33.0, 24.0, 232323222, 0.232, 0.12, "Jun 1", 12.0))
        val company = Company("APPL_iex", "symbol", "Computer Hardware", "http://www.apple.com", "Tim Cook", "Computer Company", "Tech", chart)
        Mockito.`when`(companyDetailsRepository.getCompany(company_symbol)).thenReturn(Observable.just(company))
        val observer = mock<Observer<Company>>()
        companyDetailsViewModel.loadStocks(company_symbol)
        companyDetailsViewModel.getCompany().observeForever(observer)
        verify(observer).onChanged(company)
    }

    @Test
    fun testForCompanySymbol() {
        val chart = listOf(
                ChartItem("2018-01-01", 23.0, 34.0, 2.0, 24.0, 2323232, 0.22, 0.32, "Jun 1", 12.0),
                ChartItem("2018-01-02", 24.0, 31.0, 33.0, 24.0, 232323222, 0.232, 0.12, "Jun 1", 12.0))
        val company = Company("APPL_iex", "APPL", "Computer Hardware", "http://www.apple.com", "Tim Cook", "Computer Company", "Tech", chart)
        Mockito.`when`(companyDetailsRepository.getCompany(company_symbol)).thenReturn(Observable.just(company))
        companyDetailsViewModel.loadStocks(company_symbol)
        val companyLiveData = companyDetailsViewModel.getCompany().value as Company
        assert(companyLiveData.symbol.contentEquals("APPL"))
    }

    @Test
    fun testForCompanyChart() {
        val chart = listOf(
                ChartItem("2018-01-01", 23.0, 34.0, 2.0, 24.0, 2323232, 0.22, 0.32, "Jun 1", 12.0),
                ChartItem("2018-01-02", 24.0, 31.0, 33.0, 24.0, 232323222, 0.232, 0.12, "Jun 1", 12.0))
        val company = Company("APPL_iex", "APPL", "Computer Hardware", "http://www.apple.com", "Tim Cook", "Computer Company", "Tech", chart)
        Mockito.`when`(companyDetailsRepository.getCompany(company_symbol)).thenReturn(Observable.just(company))
        companyDetailsViewModel.loadStocks(company_symbol)
        val companyLiveData = companyDetailsViewModel.getCompany().value as Company
        companyLiveData.chart?.let {
            assert(it == chart)
        }
    }

    @Test
    fun testForCompanyChartSize() {
        val chart = listOf(
                ChartItem("2018-01-01", 23.0, 34.0, 2.0, 24.0, 2323232, 0.22, 0.32, "Jun 1", 12.0),
                ChartItem("2018-01-02", 24.0, 31.0, 33.0, 24.0, 232323222, 0.232, 0.12, "Jun 1", 12.0))
        val company = Company("APPL_iex", "APPL", "Computer Hardware", "http://www.apple.com", "Tim Cook", "Computer Company", "Tech", chart)
        Mockito.`when`(companyDetailsRepository.getCompany(company_symbol)).thenReturn(Observable.just(company))
        companyDetailsViewModel.loadStocks(company_symbol)
        val companyLiveData = companyDetailsViewModel.getCompany().value as Company
        companyLiveData.chart?.let {
            assert(it.size == chart.size)
        }
    }
}