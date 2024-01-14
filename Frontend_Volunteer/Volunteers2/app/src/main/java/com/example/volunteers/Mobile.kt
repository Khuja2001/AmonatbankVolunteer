package com.example.volunteers

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.Toolbar
import com.example.volunteers.classes.AmonatMobileClient
import com.example.volunteers.classes.ApiService
import com.example.volunteers.classes.ApiService.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class Mobile : AppCompatActivity() {


    private lateinit var dateEditText: EditText
    private val calendar = Calendar.getInstance()
    private fun showDatePickerDialog() {
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun updateDateInView() {
        val myFormat = "dd.MM.yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
        dateEditText.setText(sdf.format(calendar.time))
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

//    fun showPassportTypeMenu(view: View) {
//        val popupMenu = PopupMenu(this, view)
//        popupMenu.inflate(R.menu.passport_type_menu)
//        popupMenu.setOnMenuItemClickListener { item ->
//            when (item.itemId) {
//                R.id.menu_new_passport -> {
//                    showToast("Выбран новый (пластиковый) паспорт")
//                    intent = Intent(this@Mobile, PlasticPhotoPassportPreview::class.java)
//                    startActivity(intent)
//                    true
//                }
//
//                R.id.menu_old_passport -> {
//                    showToast("Выбран старый (бумажный) паспорт")
//                    intent = Intent(this@Mobile, PaperPhotoPassportPreview::class.java)
//                    startActivity(intent)
//                    true
//                }
//
//                else -> false
//            }
//        }
//        popupMenu.show()
//    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mobile)

        ////////////////////////////////////////////////////////////////////////////////////////////////

        val toolbar = findViewById<Toolbar>(R.id.toolbarMobile)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationIcon(R.drawable.back_white)

        toolbar.setNavigationOnClickListener {
            intent = Intent(this@Mobile, MainActivity::class.java)
            startActivity(intent)
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////

        dateEditText = findViewById(R.id.dateEditText)

        dateEditText.setOnClickListener {
            showDatePickerDialog()
        }
        /////////////////////////////////GENDER//////////////////////////////////////////////////
        var gender = ""
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroupGender)

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioButtonMale -> {
                    gender = "male"
                }
                R.id.radioButtonFemale -> {
                    gender = "female"
                }
            }
        }

////////////////////////////////////////////////////////////////////////////////////////////////

        val regInAmonatMobileButton = findViewById<Button>(R.id.regInAmonatMobile)
        regInAmonatMobileButton.setOnClickListener {

/////////////////////////////////ID VOLUNTEER//////////////////////////////////////////////////
            val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            val idVolunteer = sharedPreferences.getInt("idVolunteer", -1)
/////////////////////////////////NAME//////////////////////////////////////////////////
            val nameClient0 = findViewById<EditText>(R.id.editTextFirstName)
            val nameClient = nameClient0.text.toString()
/////////////////////////////////SURNAME//////////////////////////////////////////////////
            val surnameClient0 = findViewById<EditText>(R.id.editTextLastName)
            val surnameClient = surnameClient0.text.toString()
/////////////////////////////////PATRONYMIC//////////////////////////////////////////////////
            val patronymicClient0 = findViewById<EditText>(R.id.fatherName)
            val patronymicClient = patronymicClient0.text.toString()
/////////////////////////////////PHONE//////////////////////////////////////////////////
            val phoneNumberClient0 = findViewById<EditText>(R.id.editTextPhoneNumber)
            val phoneNumberClient = phoneNumberClient0.text.toString()
/////////////////////////////////TIN//////////////////////////////////////////////////
            val tinClient0 = findViewById<EditText>(R.id.editTextINN)
            val tinClient = tinClient0.text.toString()
/////////////////////////////////EMAIL//////////////////////////////////////////////////
            val emailClient0 = findViewById<EditText>(R.id.editTextEmail)
            val emailClient = emailClient0.text.toString()
/////////////////////////////////BIRTHDAY//////////////////////////////////////////////////
            val birthDayClient = dateEditText.text.toString()

////////////////////////////////////////////////////////////////////////////////////////////////////


//            val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
//            var date: Date? = null
//
//            try {
//                date = dateFormat.parse(birthDateEditText)
//            } catch (e: ParseException) {
//                e.printStackTrace()
//            }

////////////////////////////////////////////////////////////////////////////////////////////////////

            val amonatMobileClient = AmonatMobileClient(
            idVolunteer = idVolunteer,
            nameAmonatMobileClient = nameClient,
            surnameAmonatMobileClient = surnameClient,
            patronymicAmonatMobileClient = patronymicClient,
            sexAmonatMobileClient = gender,
            phoneAmonatMobileClient = phoneNumberClient,
            tinAmonatMobileClient = tinClient,
            emailAmonatMobileClient = emailClient,
            birthdayAmonatMobileClient = birthDayClient
            )

            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.21.252:8082/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val apiService = retrofit.create(ApiService::class.java)

            val call = apiService.createAmonatMobileClient(amonatMobileClient)

            if (nameClient == "" || surnameClient == "" || gender == "" ||phoneNumberClient == "" || tinClient == "" || emailClient == "" || birthDayClient == ""){
                showToast("Заполняйте все данные")
            }

            else {

                call.enqueue(object : Callback<ApiResponse> {
                    override fun onResponse(
                        call: Call<ApiResponse>,
                        response: Response<ApiResponse>
                    ) {
                        if (response.isSuccessful) {
                            val apiResponse = response.body()
                            if (apiResponse != null) {
                                val id = apiResponse.getId()
                                showToast("Успешно добавлено. ID: $id")
                                val intent = Intent(this@Mobile, PassportType::class.java)
                                intent.putExtra("idAmonatMobileClient", id)
                                startActivity(intent)
                            } else {
                                showToast("Получен неверный ответ от сервера")
                            }
                        } else {
                            showToast("Ошибка")
                            println(" ${response.code()}")
                        }
                    }

                    override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                        showToast("Ошибка: ${t.message}")
                        println("err: ${t.message}")
                    }
                })

                println(amonatMobileClient)
                println(nameClient)
                println(surnameClient)

                finish()
            }
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////
    }

}
