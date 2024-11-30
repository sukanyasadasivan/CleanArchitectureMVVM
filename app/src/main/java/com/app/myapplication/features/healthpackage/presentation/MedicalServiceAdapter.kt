package com.app.myapplication.features.healthpackage.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.myapplication.R
import com.app.myapplication.features.healthpackage.data.MedicalService

class MedicalServiceAdapter(
    private val context: Context,
    private val medicalServices: List<MedicalService>
) : RecyclerView.Adapter<MedicalServiceAdapter.MedicalServiceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicalServiceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_medical_service, parent, false)
        return MedicalServiceViewHolder(view)
    }

    override fun onBindViewHolder(holder: MedicalServiceViewHolder, position: Int) {
        val medicalService = medicalServices[position]
        holder.bind(medicalService)
    }

    override fun getItemCount(): Int {
        return medicalServices.size
    }

    inner class MedicalServiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val medicalServiceTextView: TextView =
            itemView.findViewById(R.id.medicalServiceTextView)
        private val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)

        fun bind(medicalService: MedicalService) {
            medicalServiceTextView.text = medicalService.medicalService
            priceTextView.text = context.resources.getString(R.string.price, medicalService.price)
        }
    }
}