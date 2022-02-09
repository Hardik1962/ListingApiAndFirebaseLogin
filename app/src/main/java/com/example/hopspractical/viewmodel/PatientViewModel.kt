package com.example.hopspractical.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hopspractical.model.PatientModel
import com.example.hopspractical.model.Payload
import com.example.hopspractical.repository.PatientRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PatientViewModel constructor(private val patientRepository: PatientRepository): ViewModel(){

    val getDataList = MutableLiveData<List<Payload>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllData(){
        val response = patientRepository.getAllData()
        response.enqueue(object :Callback<PatientModel>{

            override fun onResponse(call: Call<PatientModel>, response: Response<PatientModel>) {
                if (response.isSuccessful){
                    val data = response.body()
                    getDataList.postValue(data?.payload!!)
                }else{
                    errorMessage.postValue(response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<PatientModel>, t: Throwable) {
               errorMessage.postValue(t.message)
            }

        })
    }
}