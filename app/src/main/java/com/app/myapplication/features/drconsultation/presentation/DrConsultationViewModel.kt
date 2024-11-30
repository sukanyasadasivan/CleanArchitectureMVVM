package com.app.myapplication.features.drconsultation.presentation

import androidx.lifecycle.ViewModel
import com.app.myapplication.features.drconsultation.data.Specialty
import com.app.myapplication.features.drconsultation.domain.LoadDrConsultationData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DrConsultationViewModel : ViewModel() {

    sealed class UiState {
        data object Loading : UiState()
        data class Loaded(val specialtyList: List<Specialty>) : UiState()
    }

    // Backing property to avoid state updates from other classes
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)

    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<UiState> = _uiState


    fun loadSpecialty() {
        // ideally the use-case should be injected in the constructor. Avoided di as there are only dummy data
        _uiState.value =
            UiState.Loaded(LoadDrConsultationData().loadConsultationData())

    }

}