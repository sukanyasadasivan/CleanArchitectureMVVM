package com.app.myapplication.core.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Appointments")
data class AppointmentEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val doctorId: String,
    val doctor: String,
    val doctorDetail: String,
    val specialtyId: String,
    val specialty: String,
    val price: Float
)
