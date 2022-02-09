package com.example.hopspractical.retrofit

import com.example.hopspractical.model.PatientModel
import com.example.hopspractical.model.PatientsDetails
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("patients/")
    fun getAlldata() : Call<PatientModel>

    @GET("vitals/")
    fun getPatientDetailsdata(@Query("patientId") patientId:String,@Query("MACAddress") macAddress:String) : Call<PatientsDetails>

    companion object {

        var retrofitService: RetrofitService? = null

        fun getInstance() : RetrofitService {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("http://167.71.225.187:9000/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}