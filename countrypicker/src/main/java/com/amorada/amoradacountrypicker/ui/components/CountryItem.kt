package com.amorada.amoradacountrypicker.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.amorada.amoradacountrypicker.model.Country

@Composable
fun CountryItem(
    country: Country, onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
        Text(
            text = "${country.emoji ?: ""} ${country.countryName}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = country.phoneCode, style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(
    showBackground = true, apiLevel = 33
)
@Composable
fun CountryItemPreview() {
    MaterialTheme {
        CountryItem(country = Country(
            countryName = "Peru",
            countryCode = "PE",
            phoneCode = "+51",
            currencyCode = "PEN",
            currencyName = "Sol",
            emoji = "🇵🇪"
        ), onClick = {})
    }
}