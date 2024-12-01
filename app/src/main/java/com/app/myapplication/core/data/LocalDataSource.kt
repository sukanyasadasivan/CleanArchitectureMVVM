package com.app.myapplication.core.data

import com.app.myapplication.core.data.entities.AppointmentEntity

interface LocalDataSource {
    suspend fun getAppointmentDetails(): List<AppointmentEntity>
    suspend fun insertAppointment(appointmentEntity: AppointmentEntity)
}