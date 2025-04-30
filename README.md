# AmoradaCountryPicker

**Country Picker Android Library** ✨🚀

> Librería 100% Jetpack Compose, moderna, ligera y fácil de integrar para seleccionar país, código telefónico y moneda.

---

## 📋 Funcionalidades

- Lista de países (+195) basada en estándar ISO 3166-1.
- Soporte para código de teléfono y moneda nacional.
- Selectores listos para país, moneda y código telefónico.
- Búsqueda en tiempo real y UI moderna basada en Material 3.
- Compatible con Light/Dark mode.
- Uso flexible: con componente visual o accediendo a los datos directamente.
- Código alineado a Clean Architecture, SOLID, MVVM.

---

## 🚀 Integración

### Gradle Settings

```kotlin
// settings.gradle.kts
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

### build.gradle.kts (modulo app)

```kotlin
// libs.versions.toml
[libraries]
amorada-countrypicker = { group = "com.github.AmoradaCorp", name = "AmoradaCountryPicker", version = "v1.1.0" }
```

```kotlin
// build.gradle.kts
implementation(libs.amorada.countrypicker)
```

---

## 💻 Uso rápido con componentes incluidos

### Selector de país (con bandera, código y nombre)

```kotlin
CountryPickerDropdown(
    selectedCountryCode = state.countryCode,
    onCountrySelected = {
        state = state.copy(
            countryCode = it.countryCode,
            countryName = it.countryName
        )
    },
    countries = CountryRepository.getCountries(),
    modifier = Modifier.fillMaxWidth()
)
```

### Selector de moneda (bandera, código y nombre de moneda)

```kotlin
CurrencyPickerDropdown(
    selectedCurrencyCode = state.currencyCode,
    onCurrencySelected = {
        state = state.copy(
            currencyCode = it.currencyCode,
            currencyName = it.currencyName ?: ""
        )
    },
    countries = CountryRepository.getCountries(),
    modifier = Modifier.fillMaxWidth()
)
```

### Selector de código telefónico (bandera + código)

```kotlin
PhoneCodePickerDropdown(
    selectedPhoneCode = state.phoneCode,
    onPhoneCodeSelected = {
        state = state.copy(
            phoneCode = it.phoneCode
        )
    },
    countries = CountryRepository.getCountries(),
    modifier = Modifier.fillMaxWidth()
)
```

📝 **Requiere llamar una vez:**
```kotlin
CountryRepository.loadCountries(context)
```
Idealmente en `ViewModel` o `Application`.

---

## ⚙️ Consumo manual sin componentes UI

Puedes acceder a la data para construir tu propio selector:

```kotlin
val countries = CountryRepository.getCountries()

val formattedList = countries.map {
    "${it.emoji.orEmpty()} ${it.countryCode} - ${it.countryName}"
}
```

Acceso a moneda o teléfono:

```kotlin
val phoneList = countries.map { "${it.emoji.orEmpty()} ${it.phoneCode}" }
val currencyList = countries.map { "${it.emoji.orEmpty()} ${it.currencyCode} - ${it.currencyName}" }
```

---

## ✨ Modifier recomendado

Para todos los componentes expuestos (`CountryPickerDropdown`, `CurrencyPickerDropdown`, `PhoneCodePickerDropdown`) se recomienda:

```kotlin
modifier = Modifier.fillMaxWidth()
```

Puedes ajustarlo según tu diseño (`padding`, `weight`, etc). Todos los componentes son flexibles.

---


## 🙋‍♂️ Contribuciones

Este repositorio es público y puedes hacer fork para tu propio uso.  
Sin embargo, **las contribuciones directas no están habilitadas en este momento** para mantener control sobre la evolución de la librería.

Si deseas sugerir mejoras, puedes abrir un `Issue`.

---

## 📜 Licencia

Este proyecto está licenciado bajo la licencia MIT - ver [LICENSE](LICENSE) para más detalles.
