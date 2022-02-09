package com.example.hopspractical.repository

import com.example.hopspractical.retrofit.RetrofitService

class PatientDetailsRepository constructor(private val retrofitService: RetrofitService) {
    fun getPatientsDetailsData(patientId:String,macAddress:String) = retrofitService.getPatientDetailsdata(patientId,macAddress)
}