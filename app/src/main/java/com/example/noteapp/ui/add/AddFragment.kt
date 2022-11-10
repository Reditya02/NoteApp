package com.example.noteapp.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.noteapp.TextMessage
import com.example.noteapp.data.LoginPreferences
import com.example.noteapp.data.model.Note
import com.example.noteapp.databinding.FragmentAddBinding
import com.example.noteapp.ui.ViewModelFactory

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: AddViewModel

    private lateinit var loginPreferences: LoginPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        val application = requireNotNull(this.activity).application
        val factory = ViewModelFactory(application)

        viewModel = ViewModelProvider(this, factory)[AddViewModel::class.java]

        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginPreferences = LoginPreferences(requireContext())

        binding.apply {
            btnSave.setOnClickListener {
                if (checkInput()) {
                    val note = Note(
                        title = edtTitle.text.toString(),
                        description = edtDescription.text.toString(),
                        ownerEmail = loginPreferences.loginEmail
                    )

                    saveNote(note)

                    Toast.makeText(
                        context,
                        "Berhasil membuat Note",
                        Toast.LENGTH_SHORT
                    ).show()

                    findNavController().popBackStack()
                }
            }
        }
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

    private fun saveNote(note: Note) {
        viewModel.addNote(note)
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }
}