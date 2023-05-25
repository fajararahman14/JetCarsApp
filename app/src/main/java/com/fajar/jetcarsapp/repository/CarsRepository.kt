package com.fajar.jetcarsapp.repository

import com.fajar.jetcarsapp.model.Cars
import com.fajar.jetcarsapp.model.CarsData

class CarsRepository {
    fun getCars(): List<Cars> {
        return CarsData.cars
    }

    fun getCarsById(id: String): Cars {
        return CarsData.cars.find {
            it.id == id
        } as Cars
    }

    fun searchCars(query: String): List<Cars> {
        return CarsData.cars.filter {
            it.name.contains(query, ignoreCase = true)
        }

    }

}