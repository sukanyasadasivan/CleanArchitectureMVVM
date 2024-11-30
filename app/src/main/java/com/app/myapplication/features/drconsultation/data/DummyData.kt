package com.app.myapplication.features.drconsultation.data

fun generateSpecialtyData(): List<Specialty> =
    listOf(
        Specialty(
            id = "1",
            name = "General Physician",
            price = "$50 - $100",
            doctors = listOf(
                Doctor(id = "1", name = "Dr. Smith", details = "10 years experience, MBBS"),
                Doctor(
                    id = "2",
                    name = "Dr. Alice",
                    details = "5 years experience, MD General Medicine"
                )
            )
        ),
        Specialty(
            id = "2",
            name = "Cardiologist",
            price = "$150 - $300",
            doctors = listOf(
                Doctor(
                    id = "3",
                    name = "Dr. John",
                    details = "15 years experience, MD Cardiology"
                ),
                Doctor(
                    id = "4",
                    name = "Dr. Clara",
                    details = "10 years experience, Specialist in Heart Surgeries"
                )
            )
        ),
        Specialty(
            id = "3",
            name = "Dermatologist",
            price = "$80 - $150",
            doctors = listOf(
                Doctor(
                    id = "5",
                    name = "Dr. Emily",
                    details = "12 years experience, Specialist in Skin Treatments"
                ),
                Doctor(
                    id = "6",
                    name = "Dr. Jacob",
                    details = "8 years experience, Expert in Cosmetic Dermatology"
                )
            )
        ),
        Specialty(
            id = "4",
            name = "Orthopedic",
            price = "$120 - $250",
            doctors = listOf(
                Doctor(
                    id = "7",
                    name = "Dr. Michael",
                    details = "20 years experience, Expert in Joint Replacement"
                ),
                Doctor(
                    id = "8",
                    name = "Dr. Sarah",
                    details = "15 years experience, Specialist in Sports Injuries"
                )
            )
        )
    )