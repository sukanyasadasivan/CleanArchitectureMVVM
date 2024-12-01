package com.app.myapplication.core.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.myapplication.core.data.entities.AppointmentEntity

@Dao
interface AppointmentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAppointment(appointmentEntity: AppointmentEntity)

    @Query("select * from Appointments")
    fun getAppointment(): List<AppointmentEntity>

}