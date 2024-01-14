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
import org.json.JSONStringer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class IB : AppCompatActivity() {

////////////////////////////////////////////////////////////////////////////////////////////////////

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
////////////////////////////////////////////////////////////////////////////////////////////////////


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ib)

        val toolbar = findViewById<Toolbar>(R.id.toolbarIB)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationIcon(R.drawable.back_white)

        toolbar.setNavigationOnClickListener{
            intent  = Intent(this@IB, MainActivity::class.java)
            startActivity(intent)
        }
////////////////////////////////////////////////////////////////////////////////////////////////////

        val ibButton = findViewById<Button>(R.id.ibButton)

        ibButton.setOnClickListener {
            val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            val idVolunteer = sharedPreferences.getInt("idVolunteer", -1)
            val companyName = findViewById<EditText>(R.id.editTextCompanyName)
            val companyNameString = companyName.text.toString()
            val address = findViewById<EditText>(R.id.editTextAddress)
            val addressString = address.text.toString()
            val doc = findViewById<EditText>(R.id.editTextDocument)
            val docString = doc.text.toString()
            val directorPhone = findViewById<EditText>(R.id.editTextDirectorPhone)
            val directorPhoneString = directorPhone.text.toString()
            val accountantPhone = findViewById<EditText>(R.id.editTextAccountantPhone)
            val accountantPhoneString = accountantPhone.text.toString()

////////////////////////////////////////////////////////////////////////////////////////////////////

            val ibClient = InternetBankingClient(
                idVolunteer = idVolunteer,
                nameIBClient = companyNameString,
                addressIBClient = addressString,
                tinIBClient = docString,
                phoneNumDirectorIBClient = directorPhoneString,
                phoneNumChiefAccountantIBClient = accountantPhoneString
            )

            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.21.252:8082/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val apiService = retrofit.create(ApiService::class.java)

            val call = apiService.createInternetBankingClient(ibClient)

                if (companyNameString == "" || addressString == "" || docString == "" || directorPhoneString == "" || accountantPhoneString == "")
                        {
                            showToast("Заполняйте все данные!!!")
                        }

                else {
                    call.enqueue(object : Callback<JSONStringer> {
                        override fun onResponse( call: Call<JSONStringer>, response: Response<JSONStringer> )
                        {
                            if (response.isSuccessful) {
                                val message = response.body()
                                showToast("Успешно добавлено")
                                println(message)
                            } else {
                                showToast("Ошибка")
                                println(" ${response.code()}")
                            }
                        }

                        override fun onFailure(call: Call<JSONStringer>, t: Throwable)
                        {
                            showToast("Ошибка: ${t.message}")
                            println("err: ${t.message}")
                        }
                    })
                            finish()
                }
        }
////////////////////////////////////////////////////////////////////////////////////////////////////

    }
}