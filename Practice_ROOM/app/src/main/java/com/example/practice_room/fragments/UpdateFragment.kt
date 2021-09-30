package com.example.practice_room.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.practice_room.R
import com.example.practice_room.data.Task
import com.example.practice_room.data.TaskViewModel
import com.example.practice_room.databinding.FragmentUpdateBinding
import kotlinx.android.synthetic.main.fragment_add.*


class UpdateFragment : Fragment() {
    private lateinit var binding: FragmentUpdateBinding
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(layoutInflater)
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        binding.etDataLine.setText(args.currentTask.topic)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btUpdateButton.setOnClickListener {
            updateItems()
        }
    }

    private fun updateItems() {
        val topic = et_data_line.text.toString()
        if (inputCheck(topic)) {
            val updatedTask = Task(args.currentTask.id, topic)
            taskViewModel.updateTask(updatedTask)
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill the field!!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete)
            delete_task()
        return super.onOptionsItemSelected(item)
    }

    private fun delete_task() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            taskViewModel.deleteTask(args.currentTask)
            Toast.makeText(
                requireContext(),
                "Successfully removed: ${args.currentTask.topic}",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentTask.topic}")
        builder.setMessage("Are you sure you want to delete this task?")
        builder.create().show()
    }

    private fun inputCheck(data: String): Boolean {
        return !(TextUtils.isEmpty(data))
    }
}