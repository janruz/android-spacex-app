package com.github.janruz.spacexapp.data.repositories

import com.github.janruz.spacexapp.data.local.CompanyCacheStorage
import com.github.janruz.spacexapp.data.models.Company
import com.github.janruz.spacexapp.data.models.asCompany
import com.github.janruz.spacexapp.data.networking.CompanyWebService
import com.github.janruz.spacexapp.utilities.runCatchingNetworkExceptions
import javax.inject.Inject

/**
 * The interface describing a company repository.
 */
interface CompanyRepository {
    suspend fun getCompanyFromCache(): Result<Company?>
    suspend fun fetchCompany(): Result<Company>
}

/**
 * Default implementation of the CompanyRepository interface.
 */
class CompanyRepositoryImpl @Inject constructor(
    private val companyWebService: CompanyWebService,
    private val companyCacheStorage: CompanyCacheStorage
): CompanyRepository {

    override suspend fun getCompanyFromCache(): Result<Company?> {
        return companyCacheStorage.getCompanyInfo()
    }

    override suspend fun fetchCompany(): Result<Company> = runCatchingNetworkExceptions {
        val company = companyWebService.getCompanyInfo().asCompany
        companyCacheStorage.saveCompanyInfo(company)
        company
    }
}