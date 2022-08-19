package com.github.janruz.spacexapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.janruz.spacexapp.data.models.Company
import com.github.janruz.spacexapp.data.repositories.CompanyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyViewModel @Inject constructor(
    companyRepository: CompanyRepository
): ViewModel() {

    private val _company = MutableStateFlow<Company?>(null)
    val company = _company.asStateFlow()

    init {
        viewModelScope.launch {
            val savedCompanyResult = companyRepository.getCompanyFromCache()
            if(savedCompanyResult.isSuccess) {
                savedCompanyResult.getOrNull()?.let { savedCompany ->
                    _company.emit(savedCompany)
                }
            }

            val fetchedCompanyResult = companyRepository.fetchCompany()
            if(fetchedCompanyResult.isSuccess) {
                fetchedCompanyResult.getOrNull()?.let { fetchedCompany ->
                    _company.emit(fetchedCompany)
                }
            }
        }
    }
}