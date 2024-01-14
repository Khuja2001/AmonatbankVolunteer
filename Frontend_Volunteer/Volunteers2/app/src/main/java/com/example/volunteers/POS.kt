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
import com.example.volunteers.classes.PosClient
import org.json.JSONStringer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class POS : AppCompatActivity() {

    ////////////////////////////////////////////////////////////////////////////////////////////////////

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pos)

        val toolbar = findViewById<Toolbar>(R.id.toolbarPOS)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationIcon(R.drawable.back_white)

        toolbar.setNavigationOnClickListener{
            intent  = Intent(this@POS, MainActivity::class.java)
            startActivity(intent)
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////

        val posButton = findViewById<Button>(R.id.posButton)

        posButton.setOnClickListener {
            val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            val idVolunteer = sharedPreferences.getInt("idVolunteer", -1)
            val namePosClient = findViewById<EditText>(R.id.editTextName)
            val namePosClientString = namePosClient?.text.toString()
            val tinPosClient = findViewById<EditText>(R.id.editTextPosTin)
            val tinPosClientString = tinPosClient?.text.toString()
            val addressPosClient = findViewById<EditText>(R.id.editTextAddressPos)
            val addressPosClientString = addressPosClient?.text.toString()
            val installAddressPosClient = findViewById<EditText>(R.id.installAddressPos)
            val installAddressPosClientString = installAddressPosClient?.text.toString()
            val phoneNumPosClient = findViewById<EditText>(R.id.contactsPosClient)
            val phoneNumPosClientString = phoneNumPosClient?.text.toString()

////////////////////////////////////////////////////////////////////////////////////////////////////

            val posClient = PosClient(
                idVolunteer = idVolunteer,
                namePosClient = namePosClientString,
                tinPosClient = tinPosClientString,
                addressPosClient = addressPosClientString,
                installAddressPosClient = installAddressPosClientString,
                phoneNumPosClient = phoneNumPosClientString
            )

            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.21.252:8082/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val apiService = retrofit.create(ApiService::class.java)

            val call = apiService.createPosClient(posClient)

            if (namePosClientString == "" || tinPosClientString == "" || addressPosClientString == "" || installAddressPosClientString == "" || phoneNumPosClientString == "") {
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