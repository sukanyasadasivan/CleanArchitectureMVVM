package com.app.myapplication.features.appointment.data

import com.app.myapplication.features.drconsultation.data.Doctor

data class Appointment(
    val doctor: Doctor,
    val specialtyId: String,
    val specialty: String,
    val price: Float
)