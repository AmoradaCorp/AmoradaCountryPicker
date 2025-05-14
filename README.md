# AmoradaCountryPicker

**Country Picker Android Library** ✨🚀

> Librería 100% Jetpack Compose, modular, ligera y profesional para seleccionar país, código telefónico y moneda.  
> Compatible con múltiples idiomas, animaciones, búsqueda fluida y Clean Architecture 2025.

---

## 📋 Funcionalidades

- Lista completa de países (+195) con bandera, código, moneda y teléfono.
- Selectores visuales para país, moneda y código telefónico.
- Soporte para múltiples idiomas (`en`, `es`, `pt`, `fr`, `it`, `de`, `zh`, `ar`, `hi`).
- Auto-scroll al ítem seleccionado.
- Búsqueda con teclado (`IMEAction.Done` para cerrar).
- Fallback a inglés si el idioma no está soportado.
- Consumo flexible vía `CountryProvider`.

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
amorada-countrypicker = { group = "com.github.AmoradaCorp", name = "AmoradaCountryPicker", version = "v1.6.0" }

```

```kotlin
// build.gradle.kts
implementation(libs.amorada.countrypicker)

```

---

## 💻 Uso rápido con componentes incluidos
```kotlin
val countryProvider = AssetCountryProvider(context) // detecta idioma automáticamente

//Si deseas un idioma en especifico
val countryProvider = AssetCountryProvider(context, languageCode = "fr") // francés

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
        state = state.copy(phoneCode = it.phoneCode)
    },
    countryProvider = countryProvider,
    modifier = Modifier.fillMaxWidth()
)



```
---

## 🌍 Soporte de idiomas disponibles

>> Esta librería soporta automáticamente los siguientes idiomas (si están disponibles en assets):

>> Código ISO	Idioma
>> en	Inglés
>> es	Español
>> pt	Portugués
>> fr	Francés
>> de	Alemán
>> it	Italiano
>> zh	Chino
>> ar	Árabe
>> hi	Hindi

>> Si el idioma no se encuentra, se usa countries_en.json como fallback seguro.

---

📝 **¿Qué es CountryProvider?**
```kotlin
interface CountryProvider {
    fun getCountries(): List<Country>
}

```
La librería no accede a Context ni carga datos por sí sola. La responsabilidad es del consumidor.

Ejemplo:
```kotlin
class AssetCountryProvider(
    private val context: Context,
    private val languageCode: String? = null
) : CountryProvider { ... }


```

---

## ⚙️ Consumo manual sin componentes UI

Puedes acceder a la data para construir tu propio selector:

```kotlin
val countries = countryProvider.getCountries()

val list = countries.map {
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
