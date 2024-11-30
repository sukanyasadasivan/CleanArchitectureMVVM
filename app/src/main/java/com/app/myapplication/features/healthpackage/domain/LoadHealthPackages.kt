package com.app.myapplication.features.healthpackage.domain

import com.app.myapplication.features.healthpackage.data.dummyHealthPackages

class LoadHealthPackages {
    fun loadHealthPackages() = dummyHealthPackages().sortedBy { it.price }
}