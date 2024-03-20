package com.selincengiz.news.presentation.password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.selincengiz.news.R
import com.selincengiz.news.databinding.FragmentPasswordBinding
import com.selincengiz.news.databinding.FragmentSettingBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PasswordFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentPasswordBinding

    @Inject
    lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_password, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nextButton.setOnClickListener {
            val password = binding.etPassword.text.toString()
            val passwordConfirm = binding.etPasswordConfirm.text.toString()

            if (password.isNullOrEmpty() || passwordConfirm.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "Please fill in the blanks.", Toast.LENGTH_SHORT)
                    .show()

            } else if (!password.equals(passwordConfirm)) {
                Toast.makeText(requireContext(), "Passwords are not matching.", Toast.LENGTH_SHORT)
                    .show()

            } else {
                val user = auth.currentUser

                user!!.updatePassword(password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                requireContext(),
                                "Password succesfully changed.",
                                Toast.LENGTH_SHORT
                            ).show()
                            dismiss()
                        }
                    }
            }


        }

    }


}