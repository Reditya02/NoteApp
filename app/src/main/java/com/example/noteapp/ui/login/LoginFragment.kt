package com.example.noteapp.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.noteapp.data.LoginPreferences
import com.example.noteapp.TextMessage
import com.example.noteapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    private lateinit var loginPreferences: LoginPreferences

    private var isEmailValid = false
    private var isPasswordValid = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginPreferences = LoginPreferences(requireContext())

        if (loginPreferences.isLogin) {
            val toHomeFragment = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
            findNavController().navigate(toHomeFragment)
        }

        binding.apply {
            btnLogin.setOnClickListener {
                checkLogin()
                login()
            }

            tvRegister.setOnClickListener {
                val toRegisterFragment = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
                findNavController().navigate(toRegisterFragment)
            }
        }

    }

    private fun checkLogin() {
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
            }
        }
    }

    private fun login() {
        if (isEmailValid && isPasswordValid) {
            loginPreferences.isLogin = true
            val toHomeFragment = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
            findNavController().navigate(toHomeFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}