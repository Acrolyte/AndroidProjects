package com.example.practice_room.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.practice_room.R
import com.example.practice_room.data.Task
import com.example.practice_room.data.TaskViewModel
import com.example.practice_room.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(layoutInflater)
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btAddButton.setOnClickListener {
            addData()
        }
    }

    private fun addData() {
        val data: String = binding.etDataLine.text.toString()
        if (inputCheck(data)) {
            val task = Task(0, data)
            taskViewModel.addTask(task)
            Toast.makeText(requireContext(), "Added successfully", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill the fields!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(data: String): Boolean {
        return !(TextUtils.isEmpty(data))
    }
}