package com.app.myapplication.features.drconsultation.domain

import com.app.myapplication.features.drconsultation.data.generateSpecialtyData

class LoadDrConsultationData {
    fun loadConsultationData() = generateSpecialtyData().sortedBy { it.name }
}