package com.mudassir.taskmanagerapp.ui.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.viewModels
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.mudassir.taskmanagerapp.R
import com.mudassir.taskmanagerapp.databinding.FragmentCreatTastBinding
import com.mudassir.taskmanagerapp.domain.model.Task
import com.mudassir.taskmanagerapp.ui.viewmodels.CreateTaskViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import kotlin.random.Random

@AndroidEntryPoint
class CreateTaskFragment : Fragment() {
private lateinit var binding: FragmentCreatTastBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var photoUri: Uri
    private val viewModel : CreateTaskViewModel by viewModels()


    private val locationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val granted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true
            if (granted) fetchLocation()
            else showToast("Location permission denied")
        }

    private val cameraPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (granted) openCamera()
            else showToast("Camera permission denied")
        }

    private val cameraLauncher =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success) viewModel.setImageUri(photoUri)
        }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreatTastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())

        setupObservers()
        setupClickListeners()
    }

    private fun setupObservers() {

        viewModel.imageUri.observe(viewLifecycleOwner) { uri ->
            uri?.let { binding.imgTask.setImageURI(it) }
        }

        viewModel.location.observe(viewLifecycleOwner) { location ->
            location?.let {
                binding.tvLocation.text = "Lat: ${it.first}, Lng: ${it.second}"
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.btnSubmit.isEnabled = !isLoading
        }

        viewModel.taskCreated.observe(viewLifecycleOwner) { created ->
            if (created) {
                clearForm()
                showToast("Task created successfully")
                viewModel.resetState()
            }
        }
    }

    private fun setupClickListeners() {

        binding.btnCaptureImage.setOnClickListener {
            cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
        }

        binding.btnGetLocation.setOnClickListener {
            locationPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }

        binding.btnSubmit.setOnClickListener {
            submitTask()
        }
    }


    private fun submitTask() {
        val task = Task(
            id = Random.nextInt(),
            title = binding.etTitle.text.toString(),
            description = binding.etDescription.text.toString(),
            imageUri = viewModel.imageUri.value?.toString(),
            latitude = viewModel.location.value?.first,
            longitude = viewModel.location.value?.second
        )

        viewModel.createTask(task)
    }

    @SuppressLint("MissingPermission")
    private fun fetchLocation() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                location?.let {
                    viewModel.setLocation(it.latitude, it.longitude)
                } ?: showToast("Location not available")
            }
            .addOnFailureListener {
                showToast("Failed to get location")
            }
    }

    private fun openCamera() {
        photoUri = createImageUri()
        cameraLauncher.launch(photoUri)
    }

    private fun createImageUri(): Uri {
        val file = File(
            requireContext().cacheDir,
            "task_${System.currentTimeMillis()}.jpg"
        )
        return FileProvider.getUriForFile(
            requireContext(),
            "${requireContext().packageName}.provider",
            file
        )
    }


    private fun clearForm() {
        binding.etTitle.text?.clear()
        binding.etDescription.text?.clear()
        binding.imgTask.setImageDrawable(null)
        binding.tvLocation.text = getString(R.string.location_not_fetched)
    }

    private fun showToast(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }
}