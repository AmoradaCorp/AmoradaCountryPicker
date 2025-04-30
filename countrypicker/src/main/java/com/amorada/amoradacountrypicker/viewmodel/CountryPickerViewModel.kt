package com.amorada.amoradacountrypicker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.amorada.amoradacountrypicker.model.Country
import com.amorada.amoradacountrypicker.repository.CountryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

open class CountryPickerViewModel(application: Application) : AndroidViewModel(application) {

    val _countries = MutableStateFlow<List<Country>>(emptyList())
    val countries: StateFlow<List<Country>> = _countries.asStateFlow()

    private val allCountries = mutableListOf<Country>()

    init {
        loadCountries()
    }

    private fun loadCountries() {
        CountryRepository.loadCountries(getApplication())
        allCountries.clear()
        allCountries.addAll(CountryRepository.getCountries())
        _countries.value = allCountries
    }

    fun filter(query: String) {
        val filtered = if (query.isBlank()) {
            allCountries
        } else {
            allCountries.filter {
                it.countryName.contains(query, ignoreCase = true) ||
                        it.phoneCode.contains(query) ||
                        it.currencyCode.contains(query, ignoreCase = true)
            }
        }
        _countries.value = filtered
    }
}