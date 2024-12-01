package com.app.myapplication.features.drconsultation.domain

import com.app.myapplication.features.appointment.data.Appointment
import com.app.myapplication.features.appointment.domain.repo.AppointmentRepository
import javax.inject.Inject

class SaveAppointment @Inject constructor(private val repository: AppointmentRepository) {
    suspend fun saveAppointment(appointment: Appointment) {
        repository.saveAppointments(appointment)
    }
}