package com.example.noteapp.ui.register

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.noteapp.TextMessage
import com.example.noteapp.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RegisterViewModel by viewModels()

    private var isEmailValid = false
    private var isPasswordValid = false
    private var isRetypePasswordValid = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
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

    private fun register() {
        if (isEmailValid && isPasswordValid && isRetypePasswordValid) {
            val toLoginFragment = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            findNavController().navigate(toLoginFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}