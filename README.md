# AmoradaCountryPicker

**Country Picker Android Library** ✨🚀

> Librería 100% Jetpack Compose, modular, ligera y reutilizable para seleccionar país, código telefónico y moneda. Diseñada bajo Clean Architecture, SOLID y Compose 2025.

---

## 📋 Funcionalidades

- Lista de países (+195) basada en ISO 3166-1.
- Selección de país, moneda y código telefónico.
- UX optimizada: clic en todo el campo y búsqueda en tiempo real.
- Interfaz `CountryProvider` para desacoplar la fuente de datos (assets, red, mock...).
- Soporte para tema claro/oscuro.
- Código desacoplado, testeable y profesional.

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
# libs.versions.toml
[libraries]
amorada-countrypicker = { group = "com.github.AmoradaCorp", name = "AmoradaCountryPicker", version = "v1.4.0" }

```

```kotlin
// build.gradle.kts
implementation(libs.amorada.countrypicker)
```

---

## 💻 Uso rápido con componentes incluidos
```kotlin
val countryProvider = CountryRepository(context)
```

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
    countryProvider = countryProvider,
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
            currencyName = it.currencyName.orEmpty()
        )
    },
    countryProvider = countryProvider,
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
    countryProvider = countryProvider,
    modifier = Modifier.fillMaxWidth()
)


```

📝 **¿Qué es CountryProvider?**
```kotlin
interface CountryProvider {
    fun getCountries(): List<Country>
}
```
La librería no accede a Context ni carga datos por sí sola. La responsabilidad es del consumidor.

Ejemplo:
```kotlin
class CountryRepository(private val context: Context) : CountryProvider {
    override fun getCountries(): List<Country> {
        // Cargar JSON desde assets
    }
}

```

---

## ⚙️ Consumo manual sin componentes UI

Puedes acceder a la data para construir tu propio selector:

```kotlin
val countries = countryProvider.getCountries()

val countryList = countries.map {
    "${it.emoji.orEmpty()} ${it.countryCode} - ${it.countryName}"
}

val phoneList = countries.map {
    "${it.emoji.orEmpty()} ${it.phoneCode}"
}

val currencyList = countries.map {
    "${it.emoji.orEmpty()} ${it.currencyCode} - ${it.currencyName}"
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
