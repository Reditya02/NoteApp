package com.example.noteapp.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.noteapp.TextMessage
import com.example.noteapp.data.LoginPreferences
import com.example.noteapp.data.model.User
import com.example.noteapp.databinding.FragmentLoginBinding
import com.example.noteapp.ui.ViewModelFactory

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: LoginViewModel

    private lateinit var loginPreferences: LoginPreferences

    private var isEmailValid = false
    private var isPasswordValid = false

    private var user = User()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        val application = requireNotNull(this.activity).application
        val factory = ViewModelFactory(application)

        viewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]

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

    private fun createUser() {
        binding.apply {
            user.apply {
                email = edtEmail.text.toString()
                password = edtPassword.text.toString()
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
        createUser()
        viewModel.apply {
            checkLogin(user)
            isCanLogin.observe(viewLifecycleOwner) {
                if (isEmailValid && isPasswordValid && it) {
                    loginPreferences.isLogin = true
                    loginPreferences.loginEmail = binding.edtEmail.text.toString()
                    val toHomeFragment = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                    findNavController().navigate(toHomeFragment)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}