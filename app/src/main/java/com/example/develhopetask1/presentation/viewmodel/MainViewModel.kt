package com.example.develhopetask1.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.develhopetask1.domain.interactors.GetMobileTypeUseCase
import com.example.develhopetask1.presentation.ScreenState
import com.example.develhopetask1.presentation.model.toUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MainViewModel(private val useCase: GetMobileTypeUseCase) : ViewModel() {

    private val _mobileData = MutableStateFlow<ScreenState>(ScreenState.Loading)
    val mobileData: StateFlow<ScreenState>
        get() = _mobileData

    init {
        getMobileData()
    }

    private fun getMobileData() {
        viewModelScope.launch(Dispatchers.Main) {
            val flow = useCase()
            flow.catch {
                _mobileData.emit(ScreenState.Failed(it))
            }.collect { mobileUiModel ->
                _mobileData.emit(ScreenState.Success(mobileUiModel.toUiModel()))
            }
        }
    }
}