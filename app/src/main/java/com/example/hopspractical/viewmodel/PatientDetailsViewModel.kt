package com.example.hopspractical.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hopspractical.model.PatientData
import com.example.hopspractical.model.PatientModel
import com.example.hopspractical.model.PatientsDetails
import com.example.hopspractical.model.Payload
import com.example.hopspractical.repository.PatientDetailsRepository
import com.example.hopspractical.repository.PatientRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PatientDetailsViewModel constructor(private val patientDetailsRepository: PatientDetailsRepository) :
    ViewModel() {

    val getpatientDetialsList = MutableLiveData<List<PatientData>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllData(patientId:String,macAddress:String) {
        val response = patientDetailsRepository.getPatientsDetailsData(patientId,macAddress)

        response.enqueue(object :Callback<PatientsDetails>{
            override fun onResponse(
                call: Call<PatientsDetails>,
                response: Response<PatientsDetails>
            ) {
                if (response.isSuccessful){
                    val data = response.body()
                    getpatientDetialsList.postValue(data?.patientdata!!)
                }else{
                    errorMessage.postValue(response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<PatientsDetails>, t: Throwable) {
               errorMessage.postValue(t.message)
            }

        })

    }
}