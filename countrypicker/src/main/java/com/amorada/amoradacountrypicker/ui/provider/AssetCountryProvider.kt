package com.amorada.amoradacountrypicker.ui.provider

import android.content.Context
import com.amorada.amoradacountrypicker.model.Country
import com.amorada.amoradacountrypicker.provider.CountryProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AssetCountryProvider(private val context: Context) : CountryProvider {
    override fun getCountries(): List<Country> {
        val json = context.assets.open("countries.json").bufferedReader().use { it.readText() }

        val type = object : TypeToken<List<Country>>() {}.type
        return Gson().fromJson(json, type)
    }
}