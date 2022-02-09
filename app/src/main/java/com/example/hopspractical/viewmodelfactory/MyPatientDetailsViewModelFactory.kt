package com.example.hopspractical.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hopspractical.repository.PatientDetailsRepository
import com.example.hopspractical.repository.PatientRepository
import com.example.hopspractical.viewmodel.PatientDetailsViewModel
import com.example.hopspractical.viewmodel.PatientViewModel

class MyPatientDetailsViewModelFactory constructor(private val repository: PatientDetailsRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PatientDetailsViewModel::class.java)) {
            PatientDetailsViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}