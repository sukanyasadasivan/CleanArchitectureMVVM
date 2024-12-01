package com.app.myapplication.features.appointment.data

import com.app.myapplication.core.data.LocalDataSource
import com.app.myapplication.core.data.entities.AppointmentEntity
import com.app.myapplication.features.appointment.domain.repo.AppointmentRepository
import com.app.myapplication.features.drconsultation.data.Doctor
import javax.inject.Inject

class ImpAppointmentRepository @Inject constructor(private val localDataSource: LocalDataSource) :
    AppointmentRepository {
    override suspend fun saveAppointments(appointment: Appointment) {
        localDataSource.insertAppointment(
            AppointmentEntity(
                id = 0,
                doctorId = appointment.doctor.id,
                doctor = appointment.doctor.name,
                doctorDetail = appointment.doctor.details,
                specialtyId = appointment.specialtyId,
                specialty = appointment.specialty,
                price = appointment.price
            )
        )
    }

    override suspend fun getAppointments(): List<Appointment> {
        val appointmentRoom = localDataSource.getAppointmentDetails()
        val list = mutableListOf<Appointment>()
        appointmentRoom.forEach {
            list.add(
                Appointment(
                    Doctor(
                        it.doctorId,
                        it.doctor,
                        it.doctorDetail
                    ),
                    it.specialtyId,
                    it.specialty,
                    it.price
                )
            )
        }
        return list
    }
}