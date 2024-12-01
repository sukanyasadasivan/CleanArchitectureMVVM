package com.app.myapplication.core.di

import android.app.Application
import androidx.room.Room
import com.app.myapplication.core.data.AppDatabase
import com.app.myapplication.core.data.ImpLocalDataSource
import com.app.myapplication.core.data.LocalDataSource
import com.app.myapplication.features.appointment.data.ImpAppointmentRepository
import com.app.myapplication.features.appointment.domain.repo.AppointmentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideAppointmentRepository(localDataSource: LocalDataSource): AppointmentRepository =
        ImpAppointmentRepository(
            localDataSource
        )

    @Provides
    @Singleton
    fun databaseProvider(app: Application): AppDatabase = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        "AppDatabase"
    ).fallbackToDestructiveMigration()
        .build()

    @Provides
    fun localDataSourceProvider(db: AppDatabase): LocalDataSource = ImpLocalDataSource(db)

}