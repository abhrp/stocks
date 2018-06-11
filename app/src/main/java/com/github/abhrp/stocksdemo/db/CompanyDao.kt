package com.github.abhrp.stocksdemo.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface CompanyDao {
    @Query("select * from company")
    fun getCompanies(): List<CompanyData>

    @Query("select * from company where symbol=:symbol")
    fun getCompany(symbol: String): CompanyData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCompany(companyData: CompanyData)

    @Query("delete from company")
    fun deleteAll()
}