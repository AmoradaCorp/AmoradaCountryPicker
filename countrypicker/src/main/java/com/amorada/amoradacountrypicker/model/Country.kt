package com.amorada.amoradacountrypicker.model

data class Country(
    val countryName: String,
    val countryCode: String,
    val phoneCode: String,
    val currencyCode: String,
    val currencyName: String? = null,
    val emoji: String? = null
)