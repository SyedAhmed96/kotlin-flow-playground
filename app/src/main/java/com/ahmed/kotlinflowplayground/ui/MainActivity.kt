package com.ahmed.kotlinflowplayground.ui

import com.ahmed.kotlinflowplayground.R
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.Lifecycle
import com.ahmed.kotlinflowplayground.viewmodels.MainViewModel
import kotlinx.coroutines.launch

/**
 * Main screen that demonstrates collecting multiple [kotlinx.coroutines.flow.Flow] streams:
 * - a manually incremented counter
 * - a timer emitted every second
 * - a debounced search query result
 */
class MainActivity : AppCompatActivity() {

    /** ViewModel scoped to this Activity instance. */
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // UI references from the XML layout.
        val counterText = findViewById<TextView>(R.id.counterText)
        val timerText = findViewById<TextView>(R.id.timerText)
        val searchInput = findViewById<EditText>(R.id.searchInput)
        val searchResult = findViewById<TextView>(R.id.searchResult)
        val button = findViewById<Button>(R.id.incrementBtn)

        // Increment counter on button tap.
        button.setOnClickListener {
            viewModel.increment()
        }

        // Push text changes to the ViewModel; downstream Flow handles debounce.
        searchInput.addTextChangedListener {
            viewModel.updateSearch(it.toString())
        }

        lifecycleScope.launch {
            // Collect streams only while Activity is visible to avoid leaks and wasted work.
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    viewModel.counter.collect {
                        counterText.text = "Counter: $it"
                    }
                }

                launch {
                    viewModel.timer.collect {
                        timerText.text = "Timer: $it"
                    }
                }

                launch {
                    // Show the latest debounced search message.
                    viewModel.searchResult.collect {
                        searchResult.text = it
                    }
                }
            }
        }
    }
}
