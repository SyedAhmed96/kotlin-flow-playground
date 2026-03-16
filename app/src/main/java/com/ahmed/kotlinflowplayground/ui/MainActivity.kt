package com.ahmed.kotlinflowplayground.ui

import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ahmed.kotlinflowplayground.R
import android.os.Bundle
import android.text.TextWatcher
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.Lifecycle
import com.ahmed.kotlinflowplayground.viewmodels.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val counterText = findViewById<TextView>(R.id.counterText)
        val timerText = findViewById<TextView>(R.id.timerText)
        val searchInput = findViewById<EditText>(R.id.searchInput)
        val searchResult = findViewById<TextView>(R.id.searchResult)
        val button = findViewById<Button>(R.id.incrementBtn)

        button.setOnClickListener {
            viewModel.increment()
        }

        searchInput.addTextChangedListener {
            viewModel.updateSearch(it.toString())
        }

        lifecycleScope.launch {
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
                    viewModel.searchResult.collect {
                        searchResult.text = it
                    }
                }
            }
        }
    }
}