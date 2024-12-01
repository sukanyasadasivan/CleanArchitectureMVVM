package com.app.myapplication.features.appointment.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.myapplication.features.appointment.data.Appointment
import com.app.myapplication.features.appointment.domain.usecases.LoadAppointments
import com.app.myapplication.features.drconsultation.domain.SaveAppointment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppointmentViewModel @Inject constructor(
    private val saveAppointment: SaveAppointment,
    private val loadAppointments: LoadAppointments
) : ViewModel() {

    sealed class UiState {
        data object Loading : UiState()
        data class Loaded(val appointmentList: List<Appointment>) : UiState()
    }

    // Backing property to avoid state updates from other classes
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)

    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<UiState> = _uiState

    private val _totalPrice: MutableLiveData<Double> = MutableLiveData(0.0)
    val totalPrice: LiveData<Double> = _totalPrice


    fun loadAppointmentList() {
        viewModelScope.launch {
            val appointments = loadAppointments.loadAppointments()
            _totalPrice.value = appointments.sumOf { it.price.toDouble() }
            _uiState.value = UiState.Loaded(appointments)
        }
    }

}