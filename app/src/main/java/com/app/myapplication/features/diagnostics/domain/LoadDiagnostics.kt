package com.app.myapplication.features.diagnostics.domain

import com.app.myapplication.features.diagnostics.data.dummyDiagnostics

class LoadDiagnostics {
    fun loadDiagnosticData() = dummyDiagnostics().sortedBy { it.price }
}