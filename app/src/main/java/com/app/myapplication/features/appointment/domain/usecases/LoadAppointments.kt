package com.app.myapplication.features.appointment.domain.usecases

import com.app.myapplication.features.appointment.data.Appointment
import com.app.myapplication.features.appointment.domain.repo.AppointmentRepository
import javax.inject.Inject

class LoadAppointments @Inject constructor(private val repository: AppointmentRepository) {
    suspend fun loadAppointments(): List<Appointment> {
        return repository.getAppointments()
    }
}