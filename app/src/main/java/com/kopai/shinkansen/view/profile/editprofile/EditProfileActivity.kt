package com.kopai.shinkansen.view.profile.editprofile

import android.Manifest
import android.app.DatePickerDialog
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.kopai.shinkansen.R
import com.kopai.shinkansen.databinding.ActivityEditProfileBinding
import com.kopai.shinkansen.util.getImageUri
import com.kopai.shinkansen.util.reduceFileImage
import com.kopai.shinkansen.util.uriToFile
import com.kopai.shinkansen.view.shared.TokenViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class EditProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditProfileBinding

    private var gender = ""

    private val tokenViewModel: TokenViewModel by viewModels()

    private val editProfileViewModel: EditProfileViewModel by viewModels()

    private var currentImageUri: Uri? = null

    private var userId: Int? = null

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission(),
        ) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(this, "Permission request granted", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Permission request denied", Toast.LENGTH_LONG).show()
            }
        }

    private fun allPermissionsGranted() =
        ContextCompat.checkSelfPermission(
            this,
            REQUIRED_PERMISSION,
        ) == PackageManager.PERMISSION_GRANTED

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!allPermissionsGranted()) {
            requestPermissionLauncher.launch(REQUIRED_PERMISSION)
        }

        setupSpinner()

        binding.tvGallery.setOnClickListener {
            startGallery()
        }

        binding.tvCamera.setOnClickListener {
            startCamera()
        }

        binding.edProfileBirth.setOnClickListener {
            showDatePickerDialog(binding.edProfileBirth)
        }

        binding.btnEdit.setOnClickListener {
            editProfile()
        }

        tokenViewModel.token.observe(this) { user ->
            userId = user!!.userId.toInt()
        }
    }

    private fun editProfile() {
        currentImageUri?.let { uri ->
            val photo = uriToFile(uri, this).reduceFileImage()
            val name = binding.edProfileName.text.toString()
            val email = binding.edProfileEmail.text.toString()
            val birth = binding.edProfileBirth.text.toString()
            val address = binding.edProfileAddress.text.toString()
            val phone = binding.edProfilePhone.text.toString()

//            editProfileViewModel.updateProfile(userId, name, gender, birth, email, phone, address, photo).observe(this) { result ->
//                if (result != null) {
//                    when (result) {
//                        is ResultState.Loading -> {
//                            showLoading(true)
//                        }
//
//                        is ResultState.Success -> {
//                            showToast(result.data.message ?: "Success")
//                            showLoading(false)
//                            setResult(RESULT_OK)
//                            finish()
//                        }
//
//                        is ResultState.Error -> {
//                            showToast(result.error)
//                            showLoading(false)
//                        }
//                    }
//                }
//            }
        } ?: showToast(getString(R.string.empty_image_warning))
    }

    private fun setupSpinner() {
        val items = arrayOf("Pria", "Wanita")

        val adapter = ArrayAdapter(this, R.layout.item_spinner, items)
        adapter.setDropDownViewResource(R.layout.item_spinner)
        binding.spinnerProfileGender.adapter = adapter

        binding.spinnerProfileGender.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    // Get the selected item
                    gender = parent.getItemAtPosition(position).toString()
                    // Show a toast with the selected item (or handle it as needed)
//                Toast.makeText(this@EditProfileActivity, "Selected: $gender", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Handle case when no item is selected if needed
                }
            }
    }

    private fun showDatePickerDialog(editText: EditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog =
            DatePickerDialog(
                this,
                { _, selectedYear, selectedMonth, selectedDay ->
                    val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                    editText.setText(selectedDate)
                    // Handle the selected date data as needed
                    Toast.makeText(this, "Selected Date: $selectedDate", Toast.LENGTH_SHORT).show()
                },
                year,
                month,
                day,
            )

        datePickerDialog.show()
    }

    private val launcherIntentCamera =
        registerForActivityResult(
            ActivityResultContracts.TakePicture(),
        ) { isSuccess ->
            if (isSuccess) {
                showImage()
            }
        }

    private fun startCamera() {
        currentImageUri = getImageUri(this)
        launcherIntentCamera.launch(currentImageUri!!)
    }

    private val launcherGallery =
        registerForActivityResult(
            ActivityResultContracts.PickVisualMedia(),
        ) { uri: Uri? ->
            if (uri != null) {
                currentImageUri = uri
                showImage()
            } else {
            }
        }

    private fun startGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private fun showImage() {
        currentImageUri?.let {
            binding.ivAccountProfile.setImageURI(it)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.pBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val REQUIRED_PERMISSION = Manifest.permission.CAMERA
    }
}
