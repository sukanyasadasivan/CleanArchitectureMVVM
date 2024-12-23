package com.app.myapplication.features.drconsultation.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.app.myapplication.R
import com.app.myapplication.databinding.FragmentDrconsultationBinding
import com.app.myapplication.features.appointment.data.Appointment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DrConsultationFragment : Fragment(), ClickListener {

    private lateinit var binding: FragmentDrconsultationBinding
    private val viewModel: DrConsultationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_drconsultation,
            container, false
        )

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest { uiState ->
                    when (uiState) {
                        DrConsultationViewModel.UiState.Loading -> viewModel.loadSpecialty()
                        is DrConsultationViewModel.UiState.Loaded -> {
                            binding.expandableRecyclerView.adapter =
                                SpecialtyExpandableAdaptor(
                                    uiState.specialtyList,
                                    this@DrConsultationFragment
                                )
                        }
                    }
                }
            }
        }
    }

    override fun appointmentClick(appointment: Appointment) {
        viewModel.saveAppointment(appointment)
        Toast.makeText(requireContext(), "Appointment saved", Toast.LENGTH_LONG).show()
    }

}