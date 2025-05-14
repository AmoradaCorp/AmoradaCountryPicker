package com.amorada.amoradacountrypicker.ui.provider

import android.content.Context
import com.amorada.amoradacountrypicker.model.Country
import com.amorada.amoradacountrypicker.provider.CountryProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Locale

class AssetCountryProvider(
    private val context: Context, private val languageCode: String? = null
) : CountryProvider {

    private var cachedCountries: List<Country>? = null

    override fun getCountries(): List<Country> {
        if (cachedCountries != null) return cachedCountries!!

        val localeLang = languageCode ?: Locale.getDefault().language // ej: "es", "en", etc.
        val fileName = "countries_${localeLang}.json"

        val json = try {
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            // fallback a inglés si no existe el archivo en ese idioma
            context.assets.open("countries_en.json").bufferedReader().use { it.readText() }
        }

        val type = object : TypeToken<List<Country>>() {}.type
        cachedCountries = Gson().fromJson(json, type)
        return cachedCountries!!
    }
}