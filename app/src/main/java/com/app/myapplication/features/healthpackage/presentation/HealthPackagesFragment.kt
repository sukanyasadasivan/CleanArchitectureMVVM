package com.app.myapplication.features.healthpackage.presentation

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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HealthPackagesFragment : Fragment() {

    private lateinit var binding: FragmentHealthpackagesBinding
    private val viewModel: HealthPackagesViewModel by viewModels()

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
                        HealthPackagesViewModel.UiState.Loading -> viewModel.loadHealthPackages()
                        is HealthPackagesViewModel.UiState.Loaded -> {
                            binding.recyclerView.adapter =
                                MedicalServiceAdapter(requireContext(), uiState.packageList)
                        }
                    }
                }
            }
        }
    }

}