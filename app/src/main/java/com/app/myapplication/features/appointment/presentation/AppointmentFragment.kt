package com.app.myapplication.features.appointment.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.app.myapplication.R
import com.app.myapplication.databinding.FragmentAppointmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AppointmentFragment : Fragment() {

    private lateinit var binding: FragmentAppointmentBinding
    private val viewModel: AppointmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_appointment,
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
                        AppointmentViewModel.UiState.Loading -> viewModel.loadAppointmentList()
                        is AppointmentViewModel.UiState.Loaded -> {
                            binding.totalPrice.text = requireContext().resources.getString(
                                R.string.total_price,
                                viewModel.totalPrice.value
                            )
                            binding.recyclerView.adapter =
                                AppointmentAdaptor(requireContext(), uiState.appointmentList)
                        }
                    }
                }
            }
        }
    }

}