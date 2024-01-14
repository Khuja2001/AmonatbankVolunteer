package com.example.volunteers

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.volunteers.classes.ApiService
import com.example.volunteers.classes.AuthenticationRequest
import com.example.volunteers.classes.ApiService.ApiResponseBool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Login : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.log_in)

        val inBtn = findViewById<Button>(R.id.inButton)
        val infoBtn = findViewById<Button>(R.id.infoButton)
        val logET = findViewById<EditText>(R.id.logEditText)
        val passET = findViewById<EditText>(R.id.passEditText)

////////////////////////////////////////////////////////////////////////////////////////////////////

        logET.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (logET.text.isNotEmpty() && passET.text.isNotEmpty()) {
                    inBtn.isEnabled = true
                    inBtn.setTextColor(Color.parseColor("#009b47"))

                }
                else {
                    inBtn.isEnabled = false
                    inBtn.setTextColor(Color.parseColor("#8A8A8A"))
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        passET.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (logET.text.isNotEmpty() && passET.text.isNotEmpty()) {
                    inBtn.isEnabled = true
                    inBtn.setTextColor(Color.parseColor("#009b47"))

                }
                else {
                    inBtn.isEnabled = false
                    inBtn.setTextColor(Color.parseColor("#8A8A8A"))
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })

////////////////////////////////////////////////////////////////////////////////////////////////////

        inBtn.setOnClickListener {

            val login = logET.text.toString()
            val password = passET.text.toString()

            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.21.252:8082/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val apiService = retrofit.create(ApiService::class.java)

            val authenticationRequest = AuthenticationRequest(login, password)

            val call = apiService.authenticate(authenticationRequest)


            call.enqueue(object : Callback<ApiResponseBool> {
                override fun onResponse(call: Call<ApiResponseBool>, response: Response<ApiResponseBool>) {
                    if (response.isSuccessful) {
                        val result = response.body()
                        if (result?.getCondition() == true) {
                            showToast("Аутентификация успешна\n${result.getNameSurname_volunteer()}   ${result.getId_volunteer()}     ${result.getPhone_volunteer()}")

                            ///LOGIC/////
                            ///SENDING////
                            ///MESSAGE////
                            ////TO A PHONE/////
                            ////NUMBER//////


                            val phoneVolunteer = result.getPhone_volunteer()
                            val idVolunteer = result.getId_volunteer()

                            val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                            val editor = sharedPreferences.edit()
                            if (idVolunteer != null) {
                                editor.putInt("idVolunteer", idVolunteer.toInt())
                            }
                            editor.apply()
////////////////////////////////////////////////////////////////////////////////////////////////////

                            intent = Intent(this@Login, MainActivity::class.java)
                            startActivity(intent)

                        } else {

                            showToast("Неправильный логин или пароль")
                        }
                    } else {
                        when (val errorCode = response.code()) {
                            401 -> {
                                println("Неправильный логин или пароль: $errorCode")
                                showToast("Неправильный логин или пароль: $errorCode")
                            }

                            403 -> {
                                println("Ошибка авторизации: $errorCode")
                                showToast("Ошибка авторизации: $errorCode")
                            }

                            else -> {
                                println("Произошла ошибка: $errorCode")
                                showToast("Произошла ошибка: $errorCode")
                            }
                        }
                    }
                }

                    override fun onFailure(call: Call<ApiResponseBool>, t: Throwable) {

                        showToast("Произошла ошибка сети: ${t.message}")
                        println("Произошла ошибка сети: $t.message")
                    }
                })
        }

        infoBtn.setOnClickListener {
            intent = Intent(this@Login, Info::class.java)
            startActivity(intent)
        }
    }


    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}