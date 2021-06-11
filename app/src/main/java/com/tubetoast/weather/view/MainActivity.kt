package com.tubetoast.weather.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.tubetoast.weather.databinding.ActivityMainBinding
import com.tubetoast.weather.viewmodel.entities.AppState
import com.tubetoast.weather.viewmodel.MainViewModel
import com.tubetoast.weather.viewmodel.Presenter

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val viewModel : Presenter by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getAppState().observe(this, { render(it) })
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }

    private fun render(appState: AppState?) {
        when (appState) {
            is AppState.Success -> {
                binding.progressBar.visibility = View.GONE
            }
            is AppState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.progressBar.visibility = View.GONE
                Snackbar.make(
                    binding.root,
                    appState.error?.localizedMessage as CharSequence,
                    Snackbar.LENGTH_SHORT)
                    .show()
            }
        }
    }

}