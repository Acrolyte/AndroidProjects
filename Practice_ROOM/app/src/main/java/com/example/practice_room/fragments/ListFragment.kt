package com.example.practice_room.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practice_room.R
import com.example.practice_room.data.TaskViewModel
import com.example.practice_room.databinding.FragmentListBinding
import com.example.practice_room.fragments.adapters.ListAdapter

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var mTaskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(layoutInflater)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ListAdapter()
        val recyclerView = binding.rvMain
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        mTaskViewModel.readAllData.observe(viewLifecycleOwner, Observer { task ->
            adapter.setData(task)
            if (adapter.itemCount == 0) {
                binding.ivEmptyimage.visibility = ImageView.VISIBLE
            } else
                binding.ivEmptyimage.visibility = ImageView.GONE
        })


        binding.btFloatButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteAllTask()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllTask() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mTaskViewModel.deleteAllTask()
            Toast.makeText(
                requireContext(),
                "Successfully removed all!",
                Toast.LENGTH_SHORT
            ).show()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete all?")
        builder.setMessage("Are you sure you want to delete all tasks?")
        builder.create().show()
    }

}