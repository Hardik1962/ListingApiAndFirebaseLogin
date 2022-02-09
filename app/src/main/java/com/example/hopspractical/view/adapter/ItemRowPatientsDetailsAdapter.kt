package com.example.hopspractical.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hopspractical.R
import com.example.hopspractical.model.PatientData
import com.google.android.material.textview.MaterialTextView
import kotlinx.android.synthetic.main.item_row_patient_details.view.*

class ItemRowPatientsDetailsAdapter(private val patientDetilsList:ArrayList<PatientData>):
    RecyclerView.Adapter<ItemRowPatientsDetailsAdapter.ItemRowPatientsDetailsViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemRowPatientsDetailsViewHolder {
        return ItemRowPatientsDetailsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row_patient_details,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ItemRowPatientsDetailsViewHolder,
        position: Int
    ) {
        val data = patientDetilsList[position]
        holder.vital.text = data.vital
        holder.value1.text = data.value1
        holder.value1uom.text = data.value1uom
        holder.value2.text = data.value2
        holder.value2uom.text = data.value2uom
    }

    override fun getItemCount(): Int {
        return patientDetilsList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(list:List<PatientData>){
        patientDetilsList.addAll(list)
        notifyDataSetChanged()
    }

    class ItemRowPatientsDetailsViewHolder(view:View):RecyclerView.ViewHolder(view) {

        val vital:MaterialTextView = view.tvVitalText
        val value1:MaterialTextView = view.tvValue1Text
        val value1uom:MaterialTextView = view.tvValue1oumText
        val value2:MaterialTextView = view.tvValue2Text
        val value2uom:MaterialTextView = view.tvValue2oumText
    }
}