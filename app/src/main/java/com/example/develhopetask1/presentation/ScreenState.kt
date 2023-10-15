package com.example.develhopetask1.presentation

import com.example.develhopetask1.presentation.model.MobileUiModel

sealed class ScreenState {
    class Success(val data: MobileUiModel) : ScreenState()
    class Failed(val error: Throwable) : ScreenState()
    object Loading : ScreenState()
}
