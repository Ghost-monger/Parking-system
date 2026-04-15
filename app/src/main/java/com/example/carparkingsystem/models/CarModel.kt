package com.example.carparkingsystem.models

data class CarModel(
    var carId: String? = null,
    var plate_number: String? = null,
    var vehicle_type: String? = null,
    var driver_name: String? = null,
    var phone_number: String? = null,
    var imageUrl: String? = null,
    var color: String? = null,
    var entry_time: String? = null
)
