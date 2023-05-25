package com.fajar.jetcarsapp.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.fajar.jetcarsapp.model.Cars
import com.fajar.jetcarsapp.repository.CarsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel(private val repository: CarsRepository) : ViewModel() {
    private val _groupedCars = MutableStateFlow(
        repository.getCars()
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    )
    val groupedCars: StateFlow<Map<Char, List<Cars>>> get() = _groupedCars

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) {
        _query.value = newQuery
        _groupedCars.value = repository.searchCars(_query.value)
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    }

}