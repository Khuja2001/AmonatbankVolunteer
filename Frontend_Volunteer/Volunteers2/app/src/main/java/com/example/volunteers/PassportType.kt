package com.example.volunteers

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class PassportType : AppCompatActivity (){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.passport_type)

        val id = intent.getIntExtra("idAmonatMobileClient", -1)

        val buttonPlastic = findViewById<Button>(R.id.plasticType)
        val buttonPaper = findViewById<Button>(R.id.paperType)

        buttonPlastic.setOnClickListener {
            intent = Intent(this@PassportType, PlasticPhotoPassportPreview::class.java)
            intent.putExtra("idAmonatMobileClient", id)
            startActivity(intent)
        }

        buttonPaper.setOnClickListener {
            intent = Intent(this@PassportType, PaperPhotoPassportPreview::class.java)
            intent.putExtra("idAmonatMobileClient", id)
            startActivity(intent)
        }
    }
}
