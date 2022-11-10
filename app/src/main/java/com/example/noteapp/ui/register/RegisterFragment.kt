package com.example.noteapp.ui.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.noteapp.TextMessage
import com.example.noteapp.data.model.User
import com.example.noteapp.databinding.FragmentRegisterBinding
import com.example.noteapp.ui.ViewModelFactory

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: RegisterViewModel

    private var isEmailValid = false
    private var isPasswordValid = false
    private var isRetypePasswordValid = false

    private var user = User()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        val application = requireNotNull(this.activity).application
        val factory = ViewModelFactory(application)

        viewModel = ViewModelProvider(this, factory)[RegisterViewModel::class.java]

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnRegister.setOnClickListener {
                checkRegister()
                register()
            }
            tvLogin.setOnClickListener {
                val toLogin = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                findNavController().navigate(toLogin)
            }
        }
    }

    private fun checkRegister() {
        binding.apply {
            viewModel.apply {

                checkEdtEmail(edtEmail.text.toString())
                edtEmailResponse.observe(viewLifecycleOwner) {
                    layoutEmail.isErrorEnabled = true
                    when (it) {
                        TextMessage.Ok -> {
                            isEmailValid = true
                            layoutEmail.isErrorEnabled = false
                            layoutEmail.error = null
                        }
                        TextMessage.TextEmpty -> layoutEmail.error = "Harap masukkan email"
                        TextMessage.EmailFormatInvalid -> layoutEmail.error = "Format email salah"
                        else -> Log.d("Reditya", it.toString())
                    }
                }

                checkEdtPassword(edtPassword.text.toString())
                edtPasswordResponse.observe(viewLifecycleOwner) {
                    layoutPassword.isErrorEnabled = true
                    when (it) {
                        TextMessage.Ok -> {
                            isPasswordValid = true
                            layoutPassword.isErrorEnabled = false
                            layoutPassword.error = null
                        }
                        TextMessage.TextEmpty -> layoutPassword.error = "Harap masukkan password"
                        TextMessage.PasswordTooShort -> layoutPassword.error = "Passwod minimal 8 karakter"
                        else -> Log.d("Reditya", it.toString())
                    }
                }

                checkEdtRetypePassword(edtPassword.text.toString(), edtRetypePassword.text.toString())
                edtRetypePasswordResponse.observe(viewLifecycleOwner) {
                    layoutRetypePassword.isErrorEnabled = true
                    when (it) {
                        TextMessage.Ok -> {
                            isRetypePasswordValid = true
                            layoutRetypePassword.isErrorEnabled = false
                            layoutRetypePassword.error = null
                        }
                        TextMessage.PasswordNotMatch -> layoutRetypePassword.error = "Password Tidak Sesuai"
                        else -> Log.d("Reditya", it.toString())
                    }
                }
            }
        }
    }

    private fun createUser() {
        binding.apply {
            user.apply {
                email = edtEmail.text.toString()
                password = edtPassword.text.toString()
            }
        }
    }

    private fun register() {
        createUser()
        if (isEmailValid && isPasswordValid && isRetypePasswordValid) {
            viewModel.register(user)
            val toLoginFragment = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            findNavController().navigate(toLoginFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}