package com.kopai.shinkansen.view.profile.editprofile

import android.app.DatePickerDialog
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import com.kopai.shinkansen.R
import com.kopai.shinkansen.databinding.ActivityEditProfileBinding
import com.kopai.shinkansen.databinding.ActivityRegisterBinding
import com.kopai.shinkansen.databinding.ActivitySplashBinding
import com.kopai.shinkansen.util.Constant
import com.kopai.shinkansen.util.Constant.EXTRA_PHOTO_RESULT
import com.kopai.shinkansen.util.Helper
import java.io.File
import java.util.Calendar

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding

    private var gender = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myFile = intent?.getSerializableExtra(Constant.EXTRA_PHOTO_RESULT) as File
        val isBackCamera = intent?.getBooleanExtra(Constant.EXTRA_CAMERA_MODE, true) as Boolean

        val rotatedBitmap = Helper.rotateBitmap(
            BitmapFactory.decodeFile(myFile.path),
            isBackCamera)

        setupSpinner()

        binding.edProfileBirth.setOnClickListener {
            showDatePickerDialog(binding.edProfileBirth)
        }

        binding.btnChangeImage.setOnClickListener {  }

        binding.btnEdit.setOnClickListener {
            val name =  binding.edProfileName.text.toString()
            val email = binding.edProfileEmail.text.toString()
            val birthday = binding.edProfileBirth.text.toString()
            val address = binding.edProfileAddress.text.toString()
        }
    }

    private fun setupSpinner() {

        val items = arrayOf("Pria", "Wanita")

        val adapter = ArrayAdapter(this, R.layout.item_spinner, items)
        adapter.setDropDownViewResource(R.layout.item_spinner)
        binding.spinnerProfileGender.adapter = adapter

        binding.spinnerProfileGender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // Get the selected item
                gender = parent.getItemAtPosition(position).toString()
                // Show a toast with the selected item (or handle it as needed)
                Toast.makeText(this@EditProfileActivity, "Selected: $gender", Toast.LENGTH_SHORT).show()
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

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "${selectedDay}/${selectedMonth + 1}/${selectedYear}"
                editText.setText(selectedDate)
                // Handle the selected date data as needed
                Toast.makeText(this, "Selected Date: $selectedDate", Toast.LENGTH_SHORT).show()
            },
            year,
            month,
            day
        )

        datePickerDialog.show()
    }
}