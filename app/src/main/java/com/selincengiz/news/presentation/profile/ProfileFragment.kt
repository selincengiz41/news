package com.selincengiz.news.presentation.profile

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.selincengiz.news.R
import com.selincengiz.news.common.Country.COUNTRY
import com.selincengiz.news.common.Extensions.loadUrl
import com.selincengiz.news.common.PermissionUtils
import com.selincengiz.news.common.PermissionUtils.checkPermissionn
import com.selincengiz.news.common.PermissionUtils.shouldShowRationale
import com.selincengiz.news.databinding.FragmentProfileBinding
import com.selincengiz.news.domain.entities.Countries
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment(), ItemCountryListener {
    private lateinit var binding: FragmentProfileBinding

    private val countryAdapter by lazy { CountryAdapter(this) }

    @Inject
    lateinit var auth: FirebaseAuth
    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                openGalleryIntent()

            }
        }
    private var countryList = mutableListOf<Countries>(
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        binding.countryRecycler.adapter = countryAdapter
        binding.profileFunctions = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.name.text = auth.currentUser!!.displayName
        binding.logOutButton.setOnClickListener {
            auth.signOut()
            findNavController().navigate(ProfileFragmentDirections.profileToSplash())
        }
        binding.settingsButton.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.profileToSetting())

        }
        auth.currentUser!!.photoUrl?.let {
            binding.ivPic.loadUrl(it)
        }
        countryList.add(Countries("tr", true, resources.getDrawable(R.drawable.turkey)))
        countryList.add(Countries("gb", false, resources.getDrawable(R.drawable.britain)))
        countryList.add(Countries("us", false, resources.getDrawable(R.drawable.usa)))
        countryList.add(Countries("de", false, resources.getDrawable(R.drawable.germany)))
        countryList.add(Countries("ca", false, resources.getDrawable(R.drawable.canada)))
        countryList.add(Countries("kr", false, resources.getDrawable(R.drawable.korea)))
        countryList.add(Countries("jp", false, resources.getDrawable(R.drawable.japan)))
        countryList.add(Countries("fr", false, resources.getDrawable(R.drawable.france)))
        countryList.add(Countries("it", false, resources.getDrawable(R.drawable.italy)))
        countryAdapter.submitList(countryList)


    }

    fun imageViewClicked() {

        openGalleryIntent()
        requireContext().checkPermissionn(PermissionUtils.galleryPermission,
            onGranted = {
                openGalleryIntent()
            },
            onDenied = {
                requestPermissionLauncher.launch(PermissionUtils.galleryPermission)
            })

        requireActivity().shouldShowRationale(
            PermissionUtils.galleryPermission,
            onGranted = {
                Toast.makeText(
                    requireContext(),
                    R.string.permission_required,
                    Toast.LENGTH_SHORT
                ).show()

                requestPermissionLauncher.launch(
                    PermissionUtils.galleryPermission
                )
            },
        )
    }

    private val GALLERY_REQUEST_CODE = 100
    private fun openGalleryIntent() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImageUri: Uri? = data.data
            selectedImageUri?.let {
                binding.ivPic.loadUrl(it)
                val profileUpdates = userProfileChangeRequest {

                    photoUri = it
                }
                auth.currentUser?.updateProfile(profileUpdates)
                    ?.addOnCompleteListener { task ->

                        task.addOnSuccessListener {
                            Toast.makeText(
                                requireContext(),
                                "Successfully updated profile!",
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                        task.addOnFailureListener {
                            Toast.makeText(
                                requireContext(),
                                "Profile update is failed!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    }

            }


        }
    }

    override fun onCountryClicked(country: Countries) {
        COUNTRY = country.key

        countryList.forEach {
            it.isSelected = it.key == country.key

        }
        countryAdapter.notifyDataSetChanged()
    }

}