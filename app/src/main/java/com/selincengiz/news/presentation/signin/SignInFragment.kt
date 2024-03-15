package com.selincengiz.news.presentation.signin

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.selincengiz.news.R
import com.selincengiz.news.common.LoginState
import com.selincengiz.news.databinding.FragmentSignInBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private val viewModel by viewModels<SignInViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signUpButton.setOnClickListener {
            findNavController().navigate(SignInFragmentDirections.signInToSignUp())
        }
        binding.signInButton.setOnClickListener {
            loginClicked()
        }
        observe()

    }

    fun observe() {
        lifecycleScope.launchWhenStarted {
            viewModel.loginState.collectLatest { state ->

                when (state) {
                    is LoginState.Entry -> {
                        rememberRead()
                        binding.progressBarLogin.visibility = View.GONE
                    }

                    is LoginState.Loading -> {
                        binding.progressBarLogin.visibility = View.VISIBLE
                    }

                    is LoginState.Logined -> {
                        remember()

                        binding.progressBarLogin.visibility = View.GONE

                        Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()

                        // Delay süresi sonunda başka bir işlemi gerçekleştirmek için Handler kullan
                        Handler(Looper.getMainLooper()).postDelayed(Runnable {
                            // Bu kısımda gecikme sonunda gerçekleştirilmesi istenen işlemleri yapabilirsiniz
                            // Örneğin, fragment içinde başka bir işlemi gerçekleştirmek ya da navigasyon yapmak
                            findNavController().navigate(SignInFragmentDirections.signInToHome())

                        }, 1000)

                    }

                    is LoginState.Error -> {
                        binding.progressBarLogin.visibility = View.GONE
                        Toast.makeText(
                            requireContext(),
                            state.throwable.message,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }


            }
        }

    }

    fun rememberRead() {
        val sharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(requireContext())

        val stringSet = sharedPreferences.getStringSet("user", emptySet())

        if (!stringSet.isNullOrEmpty()) {
            binding.etEmail.setText(stringSet.elementAt(0))
            binding.etPassword.setText(stringSet.elementAt(1))
            binding.cbRemember.isChecked = true
        }
    }

    fun remember() {
        if (binding.cbRemember.isChecked) {
            val sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(requireContext()).edit()

            val stringSet = mutableSetOf<String>()
            stringSet.add(binding.etEmail.text.toString())
            stringSet.add(binding.etPassword.text.toString())

            sharedPreferences.putStringSet("user", stringSet)
            sharedPreferences.apply()
        } else {
            val sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(requireContext()).edit()

            sharedPreferences.clear();
            sharedPreferences.apply();
        }
    }

    fun loginClicked() {
        viewModel.firebaseLogin(binding.etEmail.text.toString(), binding.etPassword.text.toString())
    }
}