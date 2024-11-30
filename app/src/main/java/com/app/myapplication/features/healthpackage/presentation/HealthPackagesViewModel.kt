package com.app.myapplication.features.healthpackage.presentation

import androidx.lifecycle.ViewModel
import com.app.myapplication.features.healthpackage.data.MedicalService
import com.app.myapplication.features.healthpackage.domain.LoadHealthPackages
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HealthPackagesViewModel : ViewModel() {

    sealed class UiState {
        data object Loading : UiState()
        data class Loaded(val packageList: List<MedicalService>) : UiState()
    }

    // Backing property to avoid state updates from other classes
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)

    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<UiState> = _uiState


    fun loadHealthPackages() {
        // ideally the use-case should be injected in the constructor. Avoided di as there are only dummy data
        _uiState.value =
            UiState.Loaded(LoadHealthPackages().loadHealthPackages())

    }

}