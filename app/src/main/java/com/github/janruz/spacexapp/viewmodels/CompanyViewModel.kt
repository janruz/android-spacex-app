package com.github.janruz.spacexapp.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.janruz.spacexapp.data.models.Company
import com.github.janruz.spacexapp.data.repositories.CompanyRepository
import com.github.janruz.spacexapp.utilities.Status
import com.github.janruz.spacexapp.utilities.ifSuccessGetOrNull
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyViewModel @Inject constructor(
    private val companyRepository: CompanyRepository
): ViewModel() {

    private val _companyStatus = mutableStateOf(Status.NONE)
    val companyStatus = _companyStatus as State<Status>

    private val _company = mutableStateOf<Company?>(null)
    val company = _company as State<Company?>

    private var getCompanyJob: Job? = null

    init {
        getCompany()
    }

    fun getCompany() {
        getCompanyJob?.cancel()

        getCompanyJob = viewModelScope.launch {

            if(companyStatus.value == Status.FAILURE) {
                _companyStatus.value = Status.LOADING
                delay(1000)
            } else {
                _companyStatus.value = Status.LOADING
            }

            val cacheResult = companyRepository.getCompanyFromCache().ifSuccessGetOrNull {
                _company.value = it
                _companyStatus.value = Status.SUCCESS
            }

            val apiResult = companyRepository.fetchCompany().ifSuccessGetOrNull {
                _company.value = it
                _companyStatus.value = Status.SUCCESS
            }

            if(cacheResult.isFailure && apiResult.isFailure) {
                _companyStatus.value = Status.FAILURE
            }
        }
    }
}