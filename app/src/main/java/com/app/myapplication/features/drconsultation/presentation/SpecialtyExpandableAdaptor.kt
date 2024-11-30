package com.app.myapplication.features.drconsultation.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.myapplication.R
import com.app.myapplication.features.drconsultation.data.Doctor
import com.app.myapplication.features.drconsultation.data.Specialty

class SpecialtyExpandableAdaptor(
    private val specialtyList: List<Specialty>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val expandedPositions = mutableSetOf<Int>()

    override fun getItemCount(): Int {
        var count = specialtyList.size
        specialtyList.forEachIndexed { index, specialization ->
            if (expandedPositions.contains(index)) {
                count += specialization.doctors.size
            }
        }
        return count
    }

    override fun getItemViewType(position: Int): Int {
        var cumulativeIndex = 0
        specialtyList.forEachIndexed { index, specialization ->
            if (position == cumulativeIndex) return 0 // Parent item
            if (expandedPositions.contains(index)) {
                if (position < cumulativeIndex + specialization.doctors.size + 1) return 1 // Child item
            }
            cumulativeIndex += 1 + (if (expandedPositions.contains(index)) specialization.doctors.size else 0)
        }
        throw IllegalStateException("Invalid position")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) { // Parent ViewHolder
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_specialization, parent, false)
            ParentViewHolder(view)
        } else { // Child ViewHolder
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_doctor, parent, false)
            ChildViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var cumulativeIndex = 0
        specialtyList.forEachIndexed { index, specialization ->
            if (position == cumulativeIndex) {
                (holder as ParentViewHolder).bind(specialization, index)
                return
            }
            if (expandedPositions.contains(index)) {
                val childIndex = position - cumulativeIndex - 1
                if (childIndex < specialization.doctors.size) {
                    (holder as ChildViewHolder).bind(specialization.doctors[childIndex])
                    return
                }
            }
            cumulativeIndex += 1 + (if (expandedPositions.contains(index)) specialization.doctors.size else 0)
        }
    }

    inner class ParentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val arrowImageView: ImageView = itemView.findViewById(R.id.arrowImageView)

        fun bind(specialization: Specialty, index: Int) {
            titleTextView.text = specialization.name
            arrowImageView.rotation = if (expandedPositions.contains(index)) 180f else 0f

            itemView.setOnClickListener {
                if (expandedPositions.contains(index)) {
                    expandedPositions.remove(index)
                } else {
                    expandedPositions.add(index)
                }
                notifyDataSetChanged()
            }
        }
    }

    inner class ChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val doctorNameTextView: TextView = itemView.findViewById(R.id.doctorNameTextView)
        private val doctorDetailsTextView: TextView =
            itemView.findViewById(R.id.doctorDetailsTextView)

        fun bind(doctor: Doctor) {
            doctorNameTextView.text = doctor.name
            doctorDetailsTextView.text = doctor.details
        }
    }
}