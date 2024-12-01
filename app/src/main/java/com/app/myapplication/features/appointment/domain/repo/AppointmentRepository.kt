package com.app.myapplication.features.appointment.domain.repo

import com.app.myapplication.features.appointment.data.Appointment

interface AppointmentRepository {
    suspend fun saveAppointments(appointment: Appointment)
    suspend fun getAppointments(): List<Appointment>
}