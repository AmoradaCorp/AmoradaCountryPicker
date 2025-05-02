package com.amorada.amoradacountrypicker.repository

import android.content.Context
import com.amorada.amoradacountrypicker.model.Country
import com.amorada.amoradacountrypicker.provider.CountryProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CountryRepository(private val context: Context) : CountryProvider {

    private var countries: List<Country> = emptyList()

    /**
     * Carga los países desde assets solo una vez
     */
    private fun ensureDataLoaded() {
        if (countries.isNotEmpty()) return

        val jsonString = context.assets.open("countries.json")
            .bufferedReader()
            .use { it.readText() }

        val listCountryType = object : TypeToken<List<Country>>() {}.type
        countries = Gson().fromJson(jsonString, listCountryType)
    }

    /**
     * Devuelve la lista completa de países (implementación de CountryProvider)
     */
    override fun getCountries(): List<Country> {
        ensureDataLoaded()
        return countries
    }
}