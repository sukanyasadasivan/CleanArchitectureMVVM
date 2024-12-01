package com.app.myapplication.features.appointment.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.myapplication.R
import com.app.myapplication.features.appointment.data.Appointment

class AppointmentAdaptor(
    private val context: Context,
    private val appointmentList: List<Appointment>
) :
    RecyclerView.Adapter<AppointmentAdaptor.AppointmentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_appointment, parent, false)
        return AppointmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        val medicalService = appointmentList[position]
        holder.bind(medicalService)
    }

    override fun getItemCount(): Int {
        return appointmentList.size
    }

    inner class AppointmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val specialtyTextView: TextView =
            itemView.findViewById(R.id.specialtyTextView)
        private val drTextView: TextView = itemView.findViewById(R.id.drTextView)
        private val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)

        fun bind(appointment: Appointment) {
            specialtyTextView.text = appointment.specialty
            drTextView.text = appointment.doctor.name
            priceTextView.text = context.resources.getString(R.string.price, appointment.price)
        }
    }
}