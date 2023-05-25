package com.fajar.jetcarsapp.screen.detail

import androidx.lifecycle.ViewModel
import com.fajar.jetcarsapp.model.Cars
import com.fajar.jetcarsapp.repository.CarsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailViewModel(private val repository: CarsRepository) : ViewModel() {
    fun getCars(idCars: String): StateFlow<Cars> = MutableStateFlow(
        repository.getCarsById(idCars)
    ).asStateFlow()
}
