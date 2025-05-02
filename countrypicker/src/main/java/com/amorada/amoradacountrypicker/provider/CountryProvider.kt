package com.amorada.amoradacountrypicker.provider

import com.amorada.amoradacountrypicker.model.Country

interface CountryProvider {
    fun getCountries(): List<Country>
}