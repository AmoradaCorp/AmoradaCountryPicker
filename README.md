# AmoradaCountryPicker

**Country Picker Android Library** ✨🚀

> Librería 100% Compose, moderna, ligera y fácil de integrar para seleccionar país, código telefónico y moneda.

---

## 📋 Funcionalidades

- Lista de países (+195) basada en estándar ISO 3166-1.
- Soporte para código de teléfono y moneda nacional.
- Búsqueda en tiempo real.
- UI moderna basada en Material 3.
- Compatible con Light/Dark mode.
- Pequeño tamaño de librería.
- Código alineado a Clean Architecture, SOLID, MVVM.
- Acceso a lista de países sin necesidad de usar componente visual.

---

## 🚀 Integración

**Gradle Settings**:

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
