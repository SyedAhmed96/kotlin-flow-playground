# Android Kotlin Flow Playground

A small Android demo project showcasing practical usage of Kotlin Flow and StateFlow using MVVM architecture.

This project is designed as a hands-on learning example for Android developers who want to understand reactive streams using Kotlin Coroutines.

---

## ✨ Features

The app demonstrates the following Kotlin Flow concepts:

* Basic Flow creation
* Collecting flows in UI
* StateFlow for UI state
* Timer flow emitting values periodically
* Search input with debounce
* Lifecycle aware collection

---

## 🧠 Concepts Covered

* Flow (cold streams)
* StateFlow (hot streams for UI state)
* Flow operators
* Coroutine scopes
* Lifecycle aware collection using repeatOnLifecycle

---

## 📱 Demo UI

The application contains a very simple UI used to demonstrate flows.

Components included:

* Counter text
* Increment button
* Timer text
* Search input field
* Search result text

---

## 🏗 Architecture

The project follows a simple MVVM structure.

```
UI (Activity)
     ↓
ViewModel (StateFlow)
     ↓
Flow logic
```

Directory structure:

```
app
 ├── ui
 │   └── MainActivity.kt
 │
 ├── viewmodel
 │   └── MainViewModel.kt
 │
 ├── flow
 │   └── TimerFlow.kt
 │
 └── model
```

---

## 🧪 Flow Examples Included

### Counter using StateFlow

The ViewModel exposes a counter as StateFlow.

```kotlin
private val _counter = MutableStateFlow(0)
val counter: StateFlow<Int> = _counter

fun increment() {
    _counter.value++
}
```

The Activity collects this flow and updates the UI.

---

### Timer Flow

A simple flow emitting time every second.

```kotlin
val timerFlow = flow {
    var time = 0
    while (true) {
        delay(1000)
        emit(time++)
    }
}
```

---

### Search with Debounce

Search input events are converted into a Flow and debounced.

```kotlin
searchQuery
    .debounce(500)
    .map { query ->
        "Result for $query"
    }
```

---

## ⚙️ Tech Stack

* Kotlin
* Kotlin Coroutines
* Flow / StateFlow
* Android ViewModel
* Lifecycle Runtime

---

## 🚀 Getting Started

Clone the repository

```
git clone https://github.com/SyedAhmed96/kotlin-flow-playground.git
```

Open the project in Android Studio and run the application.

---

## 📚 Learning Purpose

This project is intentionally small and focused.

The goal is to help Android developers practice:

* migrating from LiveData to Flow
* understanding reactive streams
* building lifecycle aware flow collectors

---

## 🔗 Future Improvements

Planned examples that can be added:

* SharedFlow for one-time events
* combine operator example
* flatMapLatest search example
* Room database with Flow
* Retrofit + Flow integration

---

## 🤝 Contributions

Feel free to fork the project and add additional Flow examples.

---

## 📄 License

This project is open source and available under the MIT License.
