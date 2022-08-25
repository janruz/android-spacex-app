package com.github.janruz.spacexapp.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.janruz.spacexapp.R
import com.github.janruz.spacexapp.data.models.Company
import com.github.janruz.spacexapp.data.repositories.CompanyRepository
import com.github.janruz.spacexapp.utilities.Status
import com.github.janruz.spacexapp.utilities.loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyViewModel @Inject constructor(
    private val companyRepository: CompanyRepository
): ViewModel() {

    private val _companyStatus = mutableStateOf(Status.NONE)
    val companyStatus = _companyStatus as State<Status>

    private val _messageId = MutableSharedFlow<Int>()
    val messageId = _messageId.asSharedFlow()

    private val _company = mutableStateOf<Company?>(null)
    val company = _company as State<Company?>

    private var getCompanyJob: Job? = null

    init {
        getCompany()
    }

    fun getCompany() {
        getCompanyJob?.cancel()

        getCompanyJob = viewModelScope.launch {

            _companyStatus.loading()

            val cacheResult = companyRepository.getCompanyFromCache()
            if(cacheResult.isSuccess) {
                handleSuccessResult(cacheResult)
            }

            val apiResult = companyRepository.fetchCompany()
            if(apiResult.isSuccess) {
                handleSuccessResult(apiResult)
            }

            when {
                cacheResult.isFailure && apiResult.isFailure -> {
                    _companyStatus.value = Status.FAILURE
                }
                cacheResult.isSuccess && apiResult.isFailure -> {
                    _messageId.emit(R.string.error_fetching_api_showing_data_from_cache)
                }
            }
        }
    }

    private fun handleSuccessResult(result: Result<Company?>) {
        result.getOrNull()?.let {
            _company.value = it
            _companyStatus.value = Status.SUCCESS
        }
    }
}