package com.example.hopspractical.repository

import com.example.hopspractical.retrofit.RetrofitService

class PatientRepository constructor(private val retrofitService: RetrofitService) {
    fun getAllData() = retrofitService.getAlldata()
}