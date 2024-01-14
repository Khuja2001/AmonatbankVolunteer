package com.example.volunteers

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.text.HtmlCompat

class SMScode : AppCompatActivity() {

    private lateinit var sentSmsTextView: TextView
    private var phoneNumber = "+992 98 882 11 07"


    private var countDownTimer: CountDownTimer? = null
    private var lastBackPressTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sms_code)

        val countDownTextView = findViewById<TextView>(R.id.countDown)
        val newCodeButton = findViewById<TextView>(R.id.newCodeButton)

        countDownTimer = object : CountDownTimer(60000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                val seconds = (millisUntilFinished / 1000).toInt()
                countDownTextView.text = "Повторная отправка через $seconds сек"
            }

            override fun onFinish() {
                countDownTextView.visibility = View.INVISIBLE
                newCodeButton.visibility = View.VISIBLE

            }
        }.start()


        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationIcon(R.drawable.back_white)

        toolbar.setNavigationOnClickListener{
            onBackPress()
        }


        var code : String
        val editText1 = findViewById<EditText>(R.id.editText1)
        val editText2 = findViewById<EditText>(R.id.editText2)
        val editText3 = findViewById<EditText>(R.id.editText3)
        val editText4 = findViewById<EditText>(R.id.editText4)
        editText1.requestFocus()
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
        editText1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 1) {
                    if (editText2.length()==1 && editText3.length()==1 && editText4.length()==1) {
                        code = editText1.text.toString() + editText2.text.toString() + editText3.text.toString() + editText4.text.toString()
                        if (code == "1234") {
                            val intent = Intent(this@SMScode, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                    editText2.requestFocus()
                }
            }
        })

        editText2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 1) {
                    if (editText1.length()==1 && editText3.length()==1 && editText4.length()==1) {
                        code = editText1.text.toString() + editText2.text.toString() + editText3.text.toString() + editText4.text.toString()
                        if (code == "1234") {
                            val intent = Intent(this@SMScode, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                    editText3.requestFocus()
                }
            }
        })

        editText3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 1) {
                    if (editText1.length()==1 && editText2.length()==1 && editText4.length()==1) {
                        code = editText1.text.toString() + editText2.text.toString() + editText3.text.toString() + editText4.text.toString()
                        if (code == "1234") {
                            val intent = Intent(this@SMScode, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                    editText4.requestFocus()
                }
            }
        })
        editText4.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 1 && editText1.length()==1 && editText2.length()==1 && editText3.length()==1) {
                    code = editText1.text.toString() + editText2.text.toString() + editText3.text.toString() + editText4.text.toString()
                    if (code == "1234") {
                        val intent = Intent(this@SMScode, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }
        })

        sentSmsTextView = findViewById(R.id.sentSms)
        val smsText = "Мы отправили SMS-код для входа\nв Амонат на номер <b><big><font color='#FFFFFF'>$phoneNumber</font></big></b>"
        sentSmsTextView.text = HtmlCompat.fromHtml(smsText, HtmlCompat.FROM_HTML_MODE_LEGACY)
        sentSmsTextView.textSize = 20f

        sentSmsTextView.movementMethod = LinkMovementMethod.getInstance()
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
    }
    fun newCodeButtonClick() {
        countDownTimer?.start()
    }


     private fun onBackPress() {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastBackPressTime > 1000) {
            lastBackPressTime = currentTime
        } else {
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setTitle("Выход")
            alertDialogBuilder.setMessage("@string/editTextEmail")
            alertDialogBuilder.setPositiveButton("Да") { _, _ ->
                finish()
            }
            alertDialogBuilder.setNegativeButton("Нет") { dialog, _ ->
                dialog.dismiss()
            }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }
    }
}
