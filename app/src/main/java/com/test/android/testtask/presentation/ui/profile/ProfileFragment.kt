package com.test.android.testtask.presentation.ui.profile

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.test.android.testtask.databinding.FragmentProfileBinding
import com.test.android.testtask.domain.model.UserBio
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfileBinding.inflate(layoutInflater)
        val view = binding.root

        //or sent as arguments
        viewModel.userBio.observe(viewLifecycleOwner, {
            loadProfile(it)
        })

        return view
    }



    @SuppressLint("SetTextI18n")
    private fun loadProfile(userBio: UserBio){
        binding.edName.setText(userBio.name)
        binding.edSurname.setText(userBio.second_name)
        binding.edPhone.setText(userBio.phone_code + userBio.phone_number)
    }

}