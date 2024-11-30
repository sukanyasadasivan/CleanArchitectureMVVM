package com.app.myapplication.features.drconsultation.data

data class Specialty(
    val id: String,
    val name: String,
    val price: String,
    val doctors: List<Doctor>
)
