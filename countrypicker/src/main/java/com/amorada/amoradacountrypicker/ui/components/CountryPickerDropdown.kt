package com.amorada.amoradacountrypicker.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.amorada.amoradacountrypicker.model.Country

@Composable
fun CountryPickerDropdown(
    selectedCountryCode: String,
    onCountrySelected: (Country) -> Unit,
    countries: List<Country>,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    enabled: Boolean = true,
    labelText: String = "Selecciona un país",
    placeholderText: String = "",
    textStyle: TextStyle = LocalTextStyle.current,
    labelStyle: TextStyle = MaterialTheme.typography.labelLarge
) {
    var expanded by remember { mutableStateOf(false) }

    val selectedCountry = countries.find { it.countryCode == selectedCountryCode }

    Box(modifier = modifier) {
        OutlinedTextField(value = selectedCountry?.let { "${it.emoji.orEmpty()} ${it.countryCode} - ${it.countryName}" }
            ?: "",
            onValueChange = {},
            label = { Text(text = labelText, style = labelStyle) },
            placeholder = {
                if (selectedCountry == null && placeholderText.isNotBlank()) {
                    Text(placeholderText, style = textStyle.copy(color = Color.Gray))
                }
            },
            readOnly = true,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(onClick = { expanded = true }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                }
            },
            isError = isError,
            enabled = enabled,
            textStyle = textStyle
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            countries.forEach { country ->
                DropdownMenuItem(text = {
                    Text("${country.emoji.orEmpty()} ${country.countryCode} - ${country.countryName}")
                }, onClick = {
                    onCountrySelected(country)
                    expanded = false
                })
            }
        }
    }
}