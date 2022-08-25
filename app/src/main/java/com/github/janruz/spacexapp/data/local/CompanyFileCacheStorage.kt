package com.github.janruz.spacexapp.data.local

import com.github.janruz.spacexapp.data.models.Company
import javax.inject.Inject

/**
 * Implementation of the CompanyCacheStorage that uses a plain file as the cache.
 */
class CompanyFileCacheStorage @Inject constructor(
    private val fileCacheStorage: FileCacheStorage<Company>
): CompanyCacheStorage {

    override fun getCompanyInfo(): Result<Company?> {
        return fileCacheStorage.get(fileName = CACHE_FILE_NAME)
    }

    override fun saveCompanyInfo(company: Company): Result<Unit> {
        return fileCacheStorage.save(company, fileName = CACHE_FILE_NAME)
    }

    companion object {
        private const val CACHE_FILE_NAME = "company-cache"
    }
}