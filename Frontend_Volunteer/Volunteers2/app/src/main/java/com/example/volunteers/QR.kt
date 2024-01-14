package com.example.volunteers

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.volunteers.classes.ApiService
import com.example.volunteers.classes.InternetBankingClient
import com.example.volunteers.classes.QrClient
import org.json.JSONStringer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QR : AppCompatActivity() {

    ////////////////////////////////////////////////////////////////////////////////////////////////////

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
////////////////////////////////////////////////////////////////////////////////////////////////////


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qr)

        val toolbar = findViewById<Toolbar>(R.id.toolbarQR)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationIcon(R.drawable.back_white)

        toolbar.setNavigationOnClickListener{
            intent  = Intent(this@QR, MainActivity::class.java)
            startActivity(intent)
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////

        val qrButton = findViewById<Button>(R.id.qrButton)

        qrButton.setOnClickListener {
            val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            val idVolunteer = sharedPreferences.getInt("idVolunteer", -1)
            val nameQrClient = findViewById<EditText>(R.id.editTextPointName)
            val nameQrClientString = nameQrClient.text.toString()
            val addressQrClient = findViewById<EditText>(R.id.editTextAddress)
            val addressQrClientString = addressQrClient.text.toString()
            val tinQrClient = findViewById<EditText>(R.id.editTextTin)
            val tinQrClientString = tinQrClient.text.toString()
            val ryamQrClient = findViewById<EditText>(R.id.editTextRYAM)
            val ryamQrClientString = ryamQrClient.text.toString()
            val quoteQrClient = findViewById<EditText>(R.id.editTextQuote)
            val quoteQrClientString = quoteQrClient.text.toString()
            val phoneNumDirectorQrClient = findViewById<EditText>(R.id.editTextDirectorPhone)
            val phoneNumDirectorQrClientString = phoneNumDirectorQrClient.text.toString()
            val phoneNumChiefAccountantQrClient = findViewById<EditText>(R.id.editTextAccountantPhone)
            val phoneNumChiefAccountantQrClientString = phoneNumChiefAccountantQrClient.text.toString()

////////////////////////////////////////////////////////////////////////////////////////////////////

            val qrClient = QrClient(
                idVolunteer = idVolunteer,
                nameQrClient = nameQrClientString,
                addressQrClient = addressQrClientString,
                tinQrClient = tinQrClientString,
                ryamQrClient = ryamQrClientString,
                quoteQrClient = quoteQrClientString,
                phoneNumDirectorQrClient = phoneNumDirectorQrClientString,
                phoneNumChiefAccountantQrClient = phoneNumChiefAccountantQrClientString
            )

            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.21.252:8082/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val apiService = retrofit.create(ApiService::class.java)

            val call = apiService.createQrClient(qrClient)

            if (
                nameQrClientString == "" ||
                addressQrClientString == "" ||
                tinQrClientString == "" ||
                ryamQrClientString == "" ||
                quoteQrClientString == "" ||
                phoneNumDirectorQrClientString == "" ||
                phoneNumChiefAccountantQrClientString == ""
            ) {
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
////////////////////////////////////////////////////////////////////////////////////////////////////
    }
}