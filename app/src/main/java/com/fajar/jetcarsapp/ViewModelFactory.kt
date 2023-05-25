package com.fajar.jetcarsapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fajar.jetcarsapp.repository.CarsRepository
import com.fajar.jetcarsapp.screen.detail.DetailViewModel
import com.fajar.jetcarsapp.screen.home.HomeViewModel

class ViewModelFactory(private val repository: CarsRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}