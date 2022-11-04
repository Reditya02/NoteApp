package com.example.noteapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.R
import com.example.noteapp.data.*
import com.example.noteapp.databinding.FragmentHomeBinding
import com.example.noteapp.ui.ViewModelFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HomeViewModel

    private lateinit var loginPreferences: LoginPreferences

    private lateinit var list: List<Note>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        val application = requireNotNull(this.activity).application
        val dao = NoteAppDatabase.getDatabase(application).noteDao()
        val factory = ViewModelFactory(dao)

        viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginPreferences = LoginPreferences(requireContext())

//        val factory = ViewModelFactory.getInstance(requireContext())
//        val viewModel = ViewModelProvider(this, factory!!)[HomeViewModel::class.java]

        Log.d("Reditya", "in home")

        binding.apply {
            viewModel.apply {
                //getDummyData()
                getData()
                listNote.observe(viewLifecycleOwner) {
                    list = it
                    if (list.isEmpty()) {
                        tvEmpty.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    } else {
                        tvEmpty.visibility = View.GONE
                        recyclerView.visibility = View.VISIBLE
                        showRecycler()
                    }
                }
            }

            fabAdd.setOnClickListener {
                val toAdd = HomeFragmentDirections.actionHomeFragmentToAddFragment()
                findNavController().navigate(toAdd)
            }
        }
    }

    private fun showRecycler() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = NoteAdapter(
                listNote = list,
                onClick = {
                    val toDetail = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
                    findNavController().navigate(toDetail)
                }
            )
        }
    }

    fun <T : ViewModel> T.createFactory(): ViewModelProvider.Factory {
        val viewModel = this
        return object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T = viewModel as T
        }
    }

    private fun logout() {
        loginPreferences.isLogin = false
        val toLogin = HomeFragmentDirections.actionHomeFragmentToLoginFragment()
        findNavController().navigate(toLogin)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.logout_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logout) {
            logout()
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}