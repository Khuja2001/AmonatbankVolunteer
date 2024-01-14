package com.example.volunteers

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class Other : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.other)

        val toolbar = findViewById<Toolbar>(R.id.toolbarOther)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationIcon(R.drawable.back_white)

        toolbar.setNavigationOnClickListener{
            intent  = Intent(this@Other, MainActivity::class.java)
            startActivity(intent)
        }
    }
}