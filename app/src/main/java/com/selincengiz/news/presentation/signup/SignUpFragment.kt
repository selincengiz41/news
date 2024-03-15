package com.selincengiz.news.presentation.signup

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.selincengiz.news.R
import com.selincengiz.news.common.RegisterState
import com.selincengiz.news.databinding.FragmentSignUpBinding
import com.selincengiz.news.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private val viewModel by viewModels<SignUpViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signInButton.setOnClickListener {
            findNavController().navigate(SignUpFragmentDirections.signUpToSignIn())
        }

        binding.signUpButton.setOnClickListener {
            registerClicked()
        }
        observe()
    }

    fun observe() {
        lifecycleScope.launchWhenStarted {
            viewModel.registerState.collectLatest  { state ->

                when (state) {
                    is RegisterState.Entry ->{
                        binding.progressBarRegister.visibility = View.GONE
                    }
                    is RegisterState.Loading -> {
                        binding.progressBarRegister.visibility = View.VISIBLE
                    }

                    is RegisterState.Registered -> {
                        binding.progressBarRegister.visibility = View.GONE
                        Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()

                        // Delay süresi sonunda başka bir işlemi gerçekleştirmek için Handler kullan
                        Handler(Looper.getMainLooper()).postDelayed(Runnable {
                            // Bu kısımda gecikme sonunda gerçekleştirilmesi istenen işlemleri yapabilirsiniz
                            // Örneğin, fragment içinde başka bir işlemi gerçekleştirmek ya da navigasyon yapmak
                            findNavController().navigate(SignUpFragmentDirections.signUpToHome())

                        }, 1000)

                    }

                    is RegisterState.Error -> {
                        binding.progressBarRegister.visibility = View.GONE
                        Toast.makeText(requireContext(), state.throwable.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }

    }

    fun registerClicked() {
        viewModel.firebaseRegister(
            binding.etEmail.text.toString(),
            binding.etPassword.text.toString(),
            binding.etPasswordConfirm.text.toString(),
            binding.etName.text.toString()
        )
    }

}