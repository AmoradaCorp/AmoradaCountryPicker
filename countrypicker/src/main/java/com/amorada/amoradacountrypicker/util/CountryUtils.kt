package com.amorada.amoradacountrypicker.util

import java.text.Normalizer

object CountryUtils {
    fun normalize(input: String): String {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
            .replace("\\p{Mn}+".toRegex(), "")
            .lowercase()
    }
}