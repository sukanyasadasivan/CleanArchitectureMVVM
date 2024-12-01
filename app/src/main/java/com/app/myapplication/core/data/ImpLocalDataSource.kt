package com.app.myapplication.core.data

import com.app.myapplication.core.data.entities.AppointmentEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ImpLocalDataSource(db: AppDatabase) : LocalDataSource {

    private val dao = db.appointmentDao()

    override suspend fun getAppointmentDetails(): List<AppointmentEntity> =
        withContext(Dispatchers.IO) {
            dao.getAppointment()
        }

    override suspend fun insertAppointment(appointmentEntity: AppointmentEntity) =
        withContext(Dispatchers.IO) {
            dao.saveAppointment(appointmentEntity)
        }

}