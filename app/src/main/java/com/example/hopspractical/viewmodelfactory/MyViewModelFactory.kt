package com.example.hopspractical.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hopspractical.repository.PatientRepository
import com.example.hopspractical.viewmodel.PatientViewModel

class MyViewModelFactory constructor(private val repository: PatientRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PatientViewModel::class.java)) {
            PatientViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}