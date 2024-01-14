package com.example.volunteers

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.volunteers.classes.ApiService
import com.example.volunteers.classes.SmsNotificationsClient
import org.json.JSONStringer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SMS : AppCompatActivity() {

    ////////////////////////////////////////////////////////////////////////////////////////////////////

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sms)

        val toolbar = findViewById<Toolbar>(R.id.toolbarSMS)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationIcon(R.drawable.back_white)

        toolbar.setNavigationOnClickListener {
            intent = Intent(this@SMS, MainActivity::class.java)
            startActivity(intent)
        }

        val activSmsNotifications = findViewById<Button>(R.id.activeSMSNotifications)

        activSmsNotifications.setOnClickListener {
            val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            val idVolunteer = sharedPreferences.getInt("idVolunteer", -1)

            val nameSmsNotificationsClient = findViewById<EditText>(R.id.name)
            val nameSmsNotificationsClientString = nameSmsNotificationsClient.text.toString()

            val surnameSmsNotificationsClient = findViewById<EditText>(R.id.surname)
            val surnameSmsNotificationsClientString = surnameSmsNotificationsClient.text.toString()

            val patronymicSmsNotificationsClient = findViewById<EditText>(R.id.patronymic)
            val patronymicSmsNotificationsClientString =
                patronymicSmsNotificationsClient.text.toString()

            val phoneSmsNotificationsClient = findViewById<EditText>(R.id.editTextPhoneNumber)
            val phoneSmsNotificationsClientString = phoneSmsNotificationsClient.text.toString()

            val lastFourDigitCardSmsNotificationsClient =
                findViewById<EditText>(R.id.lastFourDigits)
            val lastFourDigitCardSmsNotificationsClientString =
                lastFourDigitCardSmsNotificationsClient.text.toString()

            val commentsSmsNotificationsClient = findViewById<EditText>(R.id.comments)
            val commentsSmsNotificationsClientString =
                commentsSmsNotificationsClient.text.toString()

            val smsNotificationsClient = SmsNotificationsClient(
                idVolunteer = idVolunteer,
                nameSmsNotificationsClient = nameSmsNotificationsClientString,
                surnameSmsNotificationsClient = surnameSmsNotificationsClientString,
                patronymicSmsNotificationsClient = patronymicSmsNotificationsClientString,
                phoneSmsNotificationsClient = phoneSmsNotificationsClientString,
                lastFourDigitCardSmsNotificationsClient = lastFourDigitCardSmsNotificationsClientString,
                commentsSmsNotificationsClient = commentsSmsNotificationsClientString
            )

            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.21.252:8082/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val apiService = retrofit.create(ApiService::class.java)

            val call = apiService.createSmsNotificationsClient(smsNotificationsClient)

            if (
                nameSmsNotificationsClientString == "" ||
                surnameSmsNotificationsClientString == "" ||
                patronymicSmsNotificationsClientString == "" ||
                phoneSmsNotificationsClientString == "" ||
                lastFourDigitCardSmsNotificationsClientString == ""
                )
            {
                showToast("Заполняйте все данные!")
            }

            else {
                call.enqueue(object : Callback<JSONStringer> {
                    override fun onResponse(
                        call: Call<JSONStringer>,
                        response: Response<JSONStringer>
                    ) {
                        if (response.isSuccessful) {
                            val message = response.body()
                            showToast("Успешно добавлено")
                            println(message)
                        } else {
                            showToast("Ошибка")
                            println(" ${response.code()}")
                        }
                    }

                    override fun onFailure(call: Call<JSONStringer>, t: Throwable) {
                        showToast("Ошибка: ${t.message}")
                        println("err: ${t.message}")
                    }
                })
            }
        }
    }
}
