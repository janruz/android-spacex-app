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

/**
 * ViewModel exposing data about the company
 */
@HiltViewModel
class CompanyViewModel @Inject constructor(
    private val companyRepository: CompanyRepository
): ViewModel() {

    private val _companyStatus = mutableStateOf(Status.NONE)

    /**
     * The status of the task of getting data about the company
     */
    val companyStatus = _companyStatus as State<Status>

    private val _messageId = MutableSharedFlow<Int>()

    /**
     * String resource id of a message containing status information about tasks performed
     * by this viewModel, for example a message telling user they are in the offline mode.
     */
    val messageId = _messageId.asSharedFlow()

    private val _company = mutableStateOf<Company?>(null)

    /**
     * The company data
     */
    val company = _company as State<Company?>

    /**
     * The job of the coroutine handling getting data about the company. It is used to ensure
     * that there is always just one coroutine trying to get the company data.
     */
    private var getCompanyJob: Job? = null

    init {
        getCompany()
    }

    /**
     * Tries to first get the company data from local cache and then fetch more up-to-date
     * data from the API.
     */
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