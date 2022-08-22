package com.github.janruz.spacexapp.data.local

import com.github.janruz.spacexapp.data.models.Company
import java.io.FileNotFoundException

class FakeCompanyCacheStorage: FileCacheStorage<Company> {

    var passedFileName: String? = null
        private set

    var savedCompany: Company? = null
        private set

    var exception: Throwable? = null

    override fun get(fileName: String): Result<Company?> {
        exception?.let {
            return Result.failure(it)
        }

        if(savedCompany == null) {
            return Result.failure(FileNotFoundException())
        }

        passedFileName = fileName
        return Result.success(savedCompany)
    }

    override fun save(data: Company, fileName: String): Result<Unit> {
        exception?.let {
            return Result.failure(it)
        }

        savedCompany = data
        passedFileName = fileName
        return Result.success(Unit)
    }
}