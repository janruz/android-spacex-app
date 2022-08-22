package com.github.janruz.spacexapp.data.local

import com.github.janruz.spacexapp.data.models.Company
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import java.io.FileNotFoundException
import java.io.IOException

class CompanyFileCacheStorageTest {

    private lateinit var instance: CompanyFileCacheStorage
    private lateinit var fakeCache: FakeCompanyCacheStorage

    @Before
    fun setUp() {
        fakeCache = FakeCompanyCacheStorage()
        instance = CompanyFileCacheStorage(fakeCache)
    }

    @Test
    fun `empty cache, should return failure of file not found`() {
        val result = instance.getCompanyInfo()

        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()).isInstanceOf(FileNotFoundException::class.java)
    }

    @Test
    fun `save company and get it back, should return success of the same company`() {
        val saveResult = instance.saveCompanyInfo(company = newMockCompany(variant = 0))
        assertThat(saveResult.isSuccess).isTrue()

        val getResult = instance.getCompanyInfo()
        assertThat(getResult.isSuccess).isTrue()
        assertThat(getResult.getOrNull()).isEqualTo(newMockCompany(variant = 0))
    }

    @Test
    fun `save company and then save a new company, should return success of the new company`() {
        val saveResult0 = instance.saveCompanyInfo(company = newMockCompany(variant = 0))
        assertThat(saveResult0.isSuccess).isTrue()

        val saveResult1 = instance.saveCompanyInfo(company = newMockCompany(variant = 1))
        assertThat(saveResult1.isSuccess).isTrue()

        val getResult = instance.getCompanyInfo()
        assertThat(getResult.isSuccess).isTrue()

        val savedCompany = getResult.getOrNull()
        assertThat(savedCompany).isNotNull()
        assertThat(savedCompany).isNotEqualTo(newMockCompany(variant = 0))
        assertThat(savedCompany).isEqualTo(newMockCompany(variant = 1))
    }

    @Test
    fun `save company and get it back, should get the company from the same file`() {
        instance.saveCompanyInfo(newMockCompany(variant = 0))
        val fileName = fakeCache.passedFileName

        instance.getCompanyInfo()
        assertThat(fakeCache.passedFileName).isEqualTo(fileName)
    }

    @Test
    fun `save company and cache storage returns failure, should return it as well`() {
        val message = "Oops, boom."
        fakeCache.exception = IOException(message)

        val result = instance.saveCompanyInfo(newMockCompany(variant = 1))
        assertThat(result.isFailure).isTrue()

        val exception = result.exceptionOrNull()

        assertThat(exception).isInstanceOf(IOException::class.java)
        assertThat(exception!!.message).isEqualTo(message)
    }

    @Test
    fun `get company and cache storage returns failure, should return it as well`() {
        val message = "Oops, boom."
        fakeCache.exception = IOException(message)

        val result = instance.getCompanyInfo()

        assertThat(result.isFailure).isTrue()

        val exception = result.exceptionOrNull()

        assertThat(exception).isInstanceOf(IOException::class.java)
        assertThat(exception!!.message).isEqualTo(message)
    }

    private fun newMockCompany(variant: Int): Company {
        return when(variant) {
            0 -> Company(
                id = "das78D7A9Ddasddada980",
                founder = "Jeff Bezos",
                founded = 1995,
                employees = 40000,
                launchSites = 3,
                ceo = "Jeff Bezos",
                coo = "Miranda Kaufmann",
                cto = "George Lockwood",
                valuation = 89273309000L,
                summary = "Pretty good company."
            )
            1 -> Company(
                id = "67686daksdjnjasd889",
                founder = "Tim Cook",
                founded = 1980,
                employees = 98000,
                launchSites = 4,
                ceo = "Tim Cook",
                coo = "Jeff Williams",
                cto = "Craig Federighi",
                valuation = 20930393093033L,
                summary = "It is a great business."
            )
            else -> throw IllegalArgumentException("Wrong mock company variant number.")
        }
    }
}