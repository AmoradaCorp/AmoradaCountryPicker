package com.amorada.amoradacountrypicker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.amorada.amoradacountrypicker.model.Country
import com.amorada.amoradacountrypicker.ui.CountryPickerScreen
import com.amorada.amoradacountrypicker.viewmodel.CountryPickerViewModel

class SampleActivity : ComponentActivity() {

    private lateinit var viewModel: CountryPickerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = CountryPickerViewModel(application)

        setContent {
            MaterialTheme {
                CountryPickerScreen(
                    viewModel = viewModel,
                    onCountrySelected = { country: Country ->
                        // Puedes hacer un Log, Toast, o navegar
                        println("Selected Country: ${country.countryName} (${country.phoneCode})")
                    }
                )
            }
        }
    }
}