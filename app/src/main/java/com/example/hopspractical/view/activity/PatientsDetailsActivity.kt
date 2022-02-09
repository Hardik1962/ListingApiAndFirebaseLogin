package com.example.hopspractical.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hopspractical.R
import com.example.hopspractical.model.PatientData
import com.example.hopspractical.repository.PatientDetailsRepository
import com.example.hopspractical.retrofit.RetrofitService
import com.example.hopspractical.view.adapter.ItemRowPatientsDetailsAdapter
import com.example.hopspractical.viewmodel.PatientDetailsViewModel
import com.example.hopspractical.viewmodelfactory.MyPatientDetailsViewModelFactory
import kotlinx.android.synthetic.main.activity_patients_details.*

class PatientsDetailsActivity : AppCompatActivity() {


    val listData = ArrayList<PatientData>()
    lateinit var patientDetailsViewModel: PatientDetailsViewModel
    lateinit var patientsDetailsAdapter: ItemRowPatientsDetailsAdapter

    private val retrofitService = RetrofitService.getInstance()
    var patientID =""
    var macAddress =""
    var name =""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patients_details)
        supportActionBar?.hide()

        val newsRepository = PatientDetailsRepository(retrofitService)
        val mainViewModelProviderFactory = MyPatientDetailsViewModelFactory(newsRepository)
        patientDetailsViewModel = ViewModelProvider(this, mainViewModelProviderFactory).get(PatientDetailsViewModel::class.java)


        val intent: Bundle? = intent.extras
        if (intent != null){
            name = intent.getString("name") ?: ""
            patientID = intent.getString("patientId") ?: ""
            macAddress = intent.getString("macAddress") ?: ""

            patientDetailsViewModel.getAllData(patientID,macAddress)

        }


        tvPatientName.text = name

        patientDetailsViewModel.getpatientDetialsList.observe(this, Observer {
            patientsDetailsAdapter.addAll(it)
        })

        patientsDetailsAdapter = ItemRowPatientsDetailsAdapter(listData)

        val layoutManager = LinearLayoutManager(this)
        rcyPatientsDetails.layoutManager = layoutManager
        rcyPatientsDetails.itemAnimator = DefaultItemAnimator()
        rcyPatientsDetails.adapter = patientsDetailsAdapter

    }
}