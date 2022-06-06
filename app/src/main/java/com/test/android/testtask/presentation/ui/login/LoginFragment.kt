package com.test.android.testtask.presentation.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.test.android.testtask.R
import com.test.android.testtask.core.UIEvent
import com.test.android.testtask.core.helpers.hideKeyboard
import com.test.android.testtask.databinding.FragmentLoginBinding
import com.test.android.testtask.domain.model.UserCredentials
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(layoutInflater)
        val view = binding.root

        binding.button.isEnabled = false
        loadingState(false)
        inputListener()

        binding.button.setOnClickListener {
            view.let { activity?.hideKeyboard(it) }

            val phone = binding.edPhoneNumber.text.toString()
            val password = binding.edPassword.text.toString()

            if (phone.isEmpty() || password.isEmpty() ||
                phone.length < 5 || password.length < 5) return@setOnClickListener

            viewModel.login(
                UserCredentials(phone, password)
            )
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.eventFlow.collectLatest {
                when (it) {
                    is UIEvent.Loading -> {
                        loadingState(it.isLoading)
                        if (it.isLoading) binding.button.isEnabled = false
                    }

                    is UIEvent.Toast -> {
                        Toast.makeText(context, it.msg, Toast.LENGTH_SHORT).show()
                    }

                    is UIEvent.NavigateTo -> navigateToProfile()

                    is UIEvent.SnackBar -> {
                        loadingState(false)
                        Snackbar.make(
                            this@LoginFragment.view ?: return@collectLatest,
                            it.msg,
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }


        return view
    }

    private fun loadingState(state: Boolean) {
        binding.progressBar.isVisible = state
    }

    private fun inputListener() {
        binding.edPhoneNumber.addTextChangedListener {
            checkInputState()
        }

        binding.edPassword.addTextChangedListener {
            checkInputState()
        }
    }

    private fun checkInputState() {
        binding.button.isEnabled =
            binding.edPhoneNumber.text.isNotEmpty() && binding.edPassword.text.isNotEmpty()
    }

    private fun navigateToProfile() {
        findNavController().navigate(R.id.action_loginFragment_to_profileFragment)
    }

}