package com.mudassir.taskmanagerapp.ui.fragments

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.mudassir.taskmanagerapp.databinding.FragmentTaskDetailsBinding


class TaskDetailsFragment : Fragment() {
private lateinit var binding: FragmentTaskDetailsBinding
// I have use safe Args to transfer data between Fragments
    private val args: TaskDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val task = args.task

        binding.tvTitle.text = task.title
        binding.tvDescription.text = task.description

        if (task.latitude != null && task.longitude != null) {
            binding.tvLocation.text = "Lat: ${task.latitude}, Lng: ${task.longitude}"
        } else {
            binding.tvLocation.text = "Location not available"
        }

        task.imageUri?.let {
            binding.imgTask.visibility = View.VISIBLE
            binding.imgTask.setImageURI(Uri.parse(it))
        }
    }

}