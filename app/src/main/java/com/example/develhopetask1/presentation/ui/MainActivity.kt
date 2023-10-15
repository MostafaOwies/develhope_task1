package com.example.develhopetask1.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.develhopetask1.databinding.ActivityMainBinding
import com.example.develhopetask1.domain.interactors.GetMobileTypeUseCase
import com.example.develhopetask1.presentation.ScreenState
import com.example.develhopetask1.presentation.viewmodel.MainViewModel
import com.example.develhopetask1.presentation.viewmodel.MobileViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!
    private lateinit var myViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        collectMobileData()
    }

    private fun initViewModel() {
        val mobileViewModelFactory = MobileViewModelFactory(GetMobileTypeUseCase())
        myViewModel = ViewModelProvider(this, mobileViewModelFactory)[MainViewModel::class.java]
    }

    private fun collectMobileData() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                myViewModel.mobileData.collect {
                    when (it) {
                        is ScreenState.Success -> {
                            binding.nameTv.text = it.data.name
                        }
                        is ScreenState.Loading -> {
                        }
                        else -> {
                        }
                    }
                }
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}