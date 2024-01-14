package com.example.volunteers

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

        private var lastBackPressTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        val mobileButton = findViewById<Button>(R.id.amonatMobile)
        val ibButton = findViewById<Button>(R.id.ib)
        val posButton = findViewById<Button>(R.id.pos)
        val qrButton = findViewById<Button>(R.id.qr)
        val smsButton = findViewById<Button>(R.id.SMS)
        val otherButton = findViewById<Button>(R.id.other)

        val receivedIntent = intent
        val idVolunteer = receivedIntent.getIntExtra("idVolunteer", -1)

        mobileButton.setOnClickListener {
            intent  = Intent(this@MainActivity, Mobile::class.java)
            startActivity(intent)
        }

        ibButton.setOnClickListener {
            intent  = Intent(this@MainActivity, IB::class.java)
            startActivity(intent)
        }

        posButton.setOnClickListener {
            intent  = Intent(this@MainActivity, POS::class.java)
            startActivity(intent)
        }

        qrButton.setOnClickListener {
            intent  = Intent(this@MainActivity, QR::class.java)
            startActivity(intent)
        }

        smsButton.setOnClickListener {
            intent  = Intent(this@MainActivity, SMS::class.java)
            startActivity(intent)
        }

        otherButton.setOnClickListener {
            intent  = Intent(this@MainActivity, Other::class.java)
            startActivity(intent)
        }

    }
    @Deprecated(message = "This method is deprecated", level = DeprecationLevel.WARNING)
    override fun onBackPressed() {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastBackPressTime > 1000) {
            lastBackPressTime = currentTime
        } else {
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setTitle("Выход")
            alertDialogBuilder.setMessage("Точно хотите выйти?")
            alertDialogBuilder.setPositiveButton("Да") { _, _ ->
                finishAffinity()
            }
            alertDialogBuilder.setNegativeButton("Нет") { dialog, _ ->
                dialog.dismiss()
            }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }
    }
}