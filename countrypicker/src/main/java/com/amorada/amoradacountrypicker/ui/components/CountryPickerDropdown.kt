package com.amorada.amoradacountrypicker.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.amorada.amoradacountrypicker.model.Country
import com.amorada.amoradacountrypicker.provider.CountryProvider
import kotlinx.coroutines.delay

@Composable
fun CountryPickerDropdown(
    selectedCountryCode: String,
    onCountrySelected: (Country) -> Unit,
    countryProvider: CountryProvider,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    enabled: Boolean = true,
    labelText: String = "Selecciona un país",
    placeholderText: String = "",
    textStyle: TextStyle = LocalTextStyle.current,
    labelStyle: TextStyle = MaterialTheme.typography.labelLarge,
    dropdownPlaceholder: String = "Buscar país o código...",
    dropdownNoItemText: String = "Sin resultados"
) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val listState = rememberLazyListState()

    var expanded by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }

    val allCountries = remember(countryProvider) { countryProvider.getCountries() }
    val filteredCountries = if (searchQuery.isBlank()) allCountries else {
        allCountries.filter {
            it.countryName.contains(searchQuery, true) || it.countryCode.contains(searchQuery, true)
        }
    }

    val selectedCountry = allCountries.find { it.countryCode == selectedCountryCode }

    LaunchedEffect(expanded) {
        if (expanded) {
            delay(150)
            focusRequester.requestFocus()

            val selectedIndex =
                filteredCountries.indexOfFirst { it.countryCode == selectedCountryCode }
            if (selectedIndex >= 0) {
                listState.scrollToItem(selectedIndex)
            }
        }
    }

    Box(modifier = modifier.clickable(enabled) { expanded = true }) {
        OutlinedTextField(
            value = selectedCountry?.let { "${it.emoji.orEmpty()} ${it.countryCode} - ${it.countryName}" }
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
            modifier = Modifier
                .focusRequester(focusRequester)
                .fillMaxWidth(),
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
            expanded = expanded, onDismissRequest = {
                searchQuery = ""
                expanded = false
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .focusRequester(focusRequester),
                placeholder = { Text(dropdownPlaceholder) },
                singleLine = true,
                textStyle = textStyle,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                        expanded = false
                        searchQuery = ""
                    })
            )

            LazyColumn(state = listState) {
                items(filteredCountries) { country ->
                    DropdownMenuItem(text = {
                        Text("${country.emoji.orEmpty()} ${country.countryCode} - ${country.countryName}")
                    }, onClick = {
                        searchQuery = ""
                        onCountrySelected(country)
                        expanded = false
                    })
                }

                if (filteredCountries.isEmpty()) {
                    item {
                        DropdownMenuItem(
                            text = { Text(dropdownNoItemText, color = Color.Gray) },
                            onClick = {},
                            enabled = false
                        )
                    }
                }
            }
        }
    }
}