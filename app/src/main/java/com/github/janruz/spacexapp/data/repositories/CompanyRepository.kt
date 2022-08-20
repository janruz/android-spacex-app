package com.github.janruz.spacexapp.data.repositories

import com.github.janruz.spacexapp.data.local.CompanyCacheStorage
import com.github.janruz.spacexapp.data.models.Company
import com.github.janruz.spacexapp.data.models.asCompany
import com.github.janruz.spacexapp.data.networking.CompanyWebService
import com.github.janruz.spacexapp.data.safeApiCall
import javax.inject.Inject

interface CompanyRepository {
    suspend fun getCompanyFromCache(): Result<Company?>
    suspend fun fetchCompany(): Result<Company>
}

class CompanyRepositoryImpl @Inject constructor(
    private val companyWebService: CompanyWebService,
    private val companyCacheStorage: CompanyCacheStorage
): CompanyRepository {

    override suspend fun getCompanyFromCache(): Result<Company?> {
        return companyCacheStorage.getCompanyInfo()
    }

    override suspend fun fetchCompany(): Result<Company> = safeApiCall {
        val company = companyWebService.getCompanyInfo().asCompany
        companyCacheStorage.saveCompanyInfo(company)
        company
    }
}