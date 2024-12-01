package com.app.myapplication.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.myapplication.core.data.daos.AppointmentDao
import com.app.myapplication.core.data.entities.AppointmentEntity

@Database(entities = [AppointmentEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun appointmentDao(): AppointmentDao
}