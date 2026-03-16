package com.ahmed.kotlinflowplayground.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmed.kotlinflowplayground.flows.timerFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * Holds UI state and Flow pipelines for [com.ahmed.kotlinflowplayground.ui.MainActivity].
 */
class MainViewModel : ViewModel() {

    /** Backing state for the click counter. */
    private val _counter = MutableStateFlow(0)

    /** Public immutable counter stream. */
    val counter: StateFlow<Int> = _counter

    /**
     * Timer stream converted to [StateFlow] so collectors immediately receive latest value.
     */
    val timer = timerFlow()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            0
        )

    /** Current raw search input text. */
    val searchQuery = MutableStateFlow("")

    /**
     * Debounced search result text to avoid reacting to every single keystroke.
     */
    val searchResult =
        searchQuery
            .debounce(500)
            .map { query ->
                "Result for: $query"
            }

    /** Increments counter by one. */
    fun increment() {
        _counter.value++
    }

    /** Emits latest search query into [searchQuery]. */
    fun updateSearch(query: String) {
        viewModelScope.launch {
            searchQuery.emit(query)
        }
    }
}
