package com.example.hopspractical.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hopspractical.R
import com.example.hopspractical.listener.PatientClickListener
import com.example.hopspractical.model.Payload
import com.google.android.material.textview.MaterialTextView
import kotlinx.android.synthetic.main.item_row_patient_list.view.*

class ItemRowPatientListAdapter(private val patientList:ArrayList<Payload>,val patientClickListener: PatientClickListener):
    RecyclerView.Adapter<ItemRowPatientListAdapter.ItemRowPatientViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemRowPatientViewHolder {
        return ItemRowPatientViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row_patient_list,
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(
        holder: ItemRowPatientViewHolder,
        position: Int
    ) {
        val list = patientList[position]
        holder.fname.text = list.firstName +" "+ list.lastName
        holder.patientID.text = list.patientId
        holder.gender.text = list.gender
        holder.dob.text = list.dob

        holder.itemView.setOnClickListener {
            patientClickListener.employeeClick(position)
        }
    }

    override fun getItemCount(): Int {
        return patientList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(list:List<Payload>) {
        patientList.addAll(list)
        notifyDataSetChanged()
    }

    class ItemRowPatientViewHolder(view: View):RecyclerView.ViewHolder(view) {

        val fname: MaterialTextView = view.tvFnameText
        val patientID: MaterialTextView = view.tvPatientIdText
        val gender: MaterialTextView = view.tvGenderText
        val dob: MaterialTextView = view.tvDobText
    }
}