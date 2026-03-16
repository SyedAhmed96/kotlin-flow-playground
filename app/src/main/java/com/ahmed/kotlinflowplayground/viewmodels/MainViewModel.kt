package com.ahmed.kotlinflowplayground.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmed.kotlinflowplayground.flows.timerFlow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _counter = MutableStateFlow(0)
    val counter: StateFlow<Int> = _counter

    val timer = timerFlow()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            0
        )

    val searchQuery = MutableStateFlow("")

    val searchResult =
        searchQuery
            .debounce(500)
            .map { query ->
                "Result for: $query"
            }

    fun increment() {
        _counter.value++
    }

    fun updateSearch(query: String) {
        viewModelScope.launch {
            searchQuery.emit(query)
        }
    }
}