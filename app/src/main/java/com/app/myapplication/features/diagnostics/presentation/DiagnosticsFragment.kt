package com.app.myapplication.features.diagnostics.presentation

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
import com.app.myapplication.databinding.FragmentHealthpackagesBinding
import com.app.myapplication.features.healthpackage.presentation.MedicalServiceAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DiagnosticsFragment : Fragment() {

    private lateinit var binding: FragmentHealthpackagesBinding
    private val viewModel: DiagnosticsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_healthpackages,
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
                        DiagnosticsViewModel.UiState.Loading -> viewModel.loadDiagnostics()
                        is DiagnosticsViewModel.UiState.Loaded -> {
                            binding.recyclerView.adapter =
                                MedicalServiceAdapter(requireContext(), uiState.diagnosticList)
                        }
                    }
                }
            }
        }
    }

}