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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.amorada.amoradacountrypicker.model.Country

@Composable
fun PhoneCodePickerDropdown(
    selectedPhoneCode: String,
    onPhoneCodeSelected: (Country) -> Unit,
    countries: List<Country>,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    val selectedCountry = countries.find { it.phoneCode == selectedPhoneCode }

    Box(modifier = modifier) {
        OutlinedTextField(
            value = selectedCountry?.let { "${it.emoji.orEmpty()} ${it.phoneCode}" } ?: "",
            onValueChange = {},
            label = { Text("Código telefónico") },
            readOnly = true,
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(onClick = { expanded = true }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                }
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth().padding(4.dp)
        ) {
            countries.distinctBy { it.phoneCode }.forEach { country ->
                DropdownMenuItem(
                    text = {
                        Text("${country.emoji.orEmpty()} ${country.phoneCode}")
                    },
                    onClick = {
                        onPhoneCodeSelected(country)
                        expanded = false
                    }
                )
            }
        }
    }
}
