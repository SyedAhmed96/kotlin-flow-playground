package com.ahmed.kotlinflowplayground.flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun timerFlow(): Flow<Int> = flow {
    var time = 0
    while (true) {
        delay(1000)
        emit(time++)
    }
}