package com.ahmed.kotlinflowplayground.flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Emits an increasing integer every second, starting from 0.
 */
fun timerFlow(): Flow<Int> = flow {
    // Tracks elapsed ticks.
    var time = 0
    while (true) {
        delay(1000)
        emit(time++)
    }
}
