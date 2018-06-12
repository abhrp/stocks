package com.github.abhrp.stocksdemo.data.local.daos

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.github.abhrp.stocksdemo.data.model.Company
import io.reactivex.Flowable

@Dao
interface CompanyDao {
    @Query("select * from company")
    fun getCompanies(): Flowable<List<Company>>

    @Query("select * from company where symbol=:symbol")
    fun getCompany(symbol: String): Flowable<Company>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCompany(companyData: Company)

    @Query("delete from company")
    fun deleteAll()
}