package com.amorada.amoradacountrypicker.ui

import android.app.Application
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.amorada.amoradacountrypicker.model.Country
import com.amorada.amoradacountrypicker.ui.components.CountryItem
import com.amorada.amoradacountrypicker.viewmodel.CountryPickerViewModel

@Composable
fun CountryPickerScreen(
    viewModel: CountryPickerViewModel,
    onCountrySelected: (Country) -> Unit
) {
    val countries by viewModel.countries.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        BasicTextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                viewModel.filter(it)
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            decorationBox = { innerTextField ->
                Surface(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = MaterialTheme.shapes.medium
                ) {
                    Box(modifier = Modifier.padding(12.dp)) {
                        if (searchQuery.isEmpty()) {
                            Text("Search countries...")
                        }
                        innerTextField()
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(countries) { country ->
                CountryItem(country = country, onClick = { onCountrySelected(country) })
            }
        }
    }
}
