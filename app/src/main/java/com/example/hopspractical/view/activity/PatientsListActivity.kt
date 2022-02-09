package com.example.hopspractical.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hopspractical.R
import com.example.hopspractical.listener.PatientClickListener
import com.example.hopspractical.model.Payload
import com.example.hopspractical.repository.PatientRepository
import com.example.hopspractical.retrofit.RetrofitService
import com.example.hopspractical.view.adapter.ItemRowPatientListAdapter
import com.example.hopspractical.view.adapter.ItemRowPatientsDetailsAdapter
import com.example.hopspractical.viewmodel.PatientViewModel
import com.example.hopspractical.viewmodelfactory.MyViewModelFactory
import kotlinx.android.synthetic.main.activity_patient_list.*

class PatientsListActivity : AppCompatActivity(),PatientClickListener  {

    val listData = ArrayList<Payload>()
    lateinit var patientViewModel: PatientViewModel
    lateinit var patientListAdapter: ItemRowPatientListAdapter

    private val retrofitService = RetrofitService.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_list)

        supportActionBar?.hide()

        patientViewModel =ViewModelProvider(this,MyViewModelFactory(PatientRepository(retrofitService)))[PatientViewModel::class.java]

        patientViewModel.getDataList.observe(this, Observer {

            patientListAdapter.addAll(it)
        })

        patientListAdapter = ItemRowPatientListAdapter(listData,this)

        val layoutManager = LinearLayoutManager(this)
        rcyPatientList.layoutManager = layoutManager
        rcyPatientList.itemAnimator = DefaultItemAnimator()
        rcyPatientList.adapter = patientListAdapter


        patientViewModel.getAllData()
    }

    override fun employeeClick(position: Int) {
        val name = listData[position].firstName+ " "+ listData[position].lastName
        val patientId = listData[position].patientId
        val macAddress = listData[position].mACAddress
        val intent = Intent(this,PatientsDetailsActivity::class.java)
        intent.putExtra("name",name)
        intent.putExtra("patientId",patientId)
        intent.putExtra("macAddress",macAddress)
        startActivity(intent)
    }
}