package com.example.noteapp.ui.detail

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.noteapp.R
import com.example.noteapp.TextMessage
import com.example.noteapp.data.LoginPreferences
import com.example.noteapp.data.model.Note
import com.example.noteapp.databinding.FragmentDetailBinding
import com.example.noteapp.ui.ViewModelFactory

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var note: Note

    private lateinit var viewModel: DetailViewModel

    private lateinit var loginPreferences: LoginPreferences

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
        val factory = ViewModelFactory(application)

        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        note = DetailFragmentArgs.fromBundle(arguments as Bundle).note

        loginPreferences = LoginPreferences(requireContext())

        binding.apply {
            edtTitle.setText(note.title)
            edtDescription.setText(note.description)

            btnSave.setOnClickListener {
                if (checkInput()) {
                    note = Note(
                        id = note.id,
                        title = edtTitle.text.toString(),
                        description = edtDescription.text.toString(),
                        ownerEmail = loginPreferences.loginEmail
                    )

                    editNote(note)

                    Toast.makeText(
                        context,
                        "Berhasil mengupdate Note",
                        Toast.LENGTH_SHORT
                    ).show()

                    findNavController().popBackStack()
                }
            }
        }
    }

    private fun editNote(note: Note) {
        viewModel.editNote(note)
    }

    private fun checkInput(): Boolean {
        binding.apply {
            viewModel.apply {

                var isTitleValid = false
                checkEdtTitle(edtTitle.text.toString())
                edtTitleResponse.observe(viewLifecycleOwner) {
                    layoutTitle.isErrorEnabled = true
                    isTitleValid = when (it) {
                        TextMessage.Ok -> {
                            layoutTitle.isErrorEnabled = false
                            true
                        }
                        else -> {
                            layoutTitle.error = "Harap masukkan judul"
                            false
                        }
                    }
                }

                var isDescriptionValid = false
                checkEdtDescription(edtDescription.text.toString())
                edtDescriptionResponse.observe(viewLifecycleOwner) {
                    layoutDescription.isErrorEnabled = true
                    isDescriptionValid = when (it) {
                        TextMessage.Ok -> {
                            layoutDescription.isErrorEnabled = false
                            true
                        }
                        else -> {
                            layoutDescription.error = "Harap masukkan deskripsi"
                            false
                        }
                    }
                }
                return isTitleValid && isDescriptionValid
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete) {
            viewModel.deleteNote(note)

            Toast.makeText(
                context,
                "Berhasil menghapus Note",
                Toast.LENGTH_SHORT
            ).show()

            findNavController().popBackStack()
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}