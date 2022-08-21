package com.github.janruz.spacexapp.data.local

import com.github.janruz.spacexapp.data.models.Company
import javax.inject.Inject

interface CompanyCacheStorage {
    fun getCompanyInfo(): Result<Company?>
    fun saveCompanyInfo(company: Company): Result<Unit>
}