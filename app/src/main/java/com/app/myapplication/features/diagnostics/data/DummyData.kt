package com.app.myapplication.features.diagnostics.data

import com.app.myapplication.features.healthpackage.data.MedicalService

fun dummyDiagnostics() = listOf(
    MedicalService(id = 1, medicalService = "Blood Test", price = 50.0f),
    MedicalService(id = 2, medicalService = "MRI Scan", price = 500.0f)
)