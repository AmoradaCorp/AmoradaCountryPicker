package com.amorada.amoradacountrypicker.repository

import android.content.Context
import com.amorada.amoradacountrypicker.model.Country
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object CountryRepository {

    private var countries: List<Country> = emptyList()

    /**
     * Carga los países desde el archivo countries.json ubicado en assets
     */
    fun loadCountries(context: Context) {
        if (countries.isNotEmpty()) return

        val jsonString = context.assets.open("countries.json")
            .bufferedReader()
            .use { it.readText() }

        val listCountryType = object : TypeToken<List<Country>>() {}.type
        countries = Gson().fromJson(jsonString, listCountryType)
    }

    /**
     * Devuelve todos los países cargados
     */
    fun getCountries(): List<Country> = countries
}