package com.example.volunteers

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.volunteers.classes.ApiService
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONStringer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PlasticPhotoPassportPreview : AppCompatActivity() {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    private lateinit var profileImageView: ImageView
    private lateinit var uploadButton: Button
    private lateinit var pathProfilePhotoAmonatMobileClient: String

    private fun processAndSetImage(uri: Uri?) {
        uri?.let {
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, it)
            val scaledBitmap = Bitmap.createScaledBitmap(bitmap, 300, 300, true)
            val roundedBitmap = getRoundedBitmap(scaledBitmap)
            profileImageView.setImageBitmap(roundedBitmap)

            pathProfilePhotoAmonatMobileClient = uri.toString()
        }
    }

    private fun getRoundedBitmap(bitmap: Bitmap): Bitmap {
        val output = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output)
        val color = Color.RED
        val paint = Paint()
        val rect = Rect(0, 0, bitmap.width, bitmap.height)

        paint.isAntiAlias = true
        canvas.drawARGB(0, 0, 0, 0)
        paint.color = color
        canvas.drawCircle(bitmap.width / 2f, bitmap.height / 2f, bitmap.width / 2f, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmap, rect, rect, paint)

        return output
    }

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        profile = uri
        processAndSetImage(uri)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    private var frontPlastic: Uri? = null
    private var backPlastic: Uri? = null
    private var selfiePlastic: Uri? = null
    private var profile: Uri? = null

    private lateinit var frontPlasticPassportImage: ImageView

    private fun processAndSetImage1(uri: Uri?) {
        uri?.let {
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, it)
            val scaledBitmap = Bitmap.createScaledBitmap(bitmap, 1000, 750, true)

            frontPlasticPassportImage.setImageBitmap(scaledBitmap)
        }
    }

    private val getContent1 =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            frontPlastic = uri
            processAndSetImage1(uri)
        }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private lateinit var backPlasticPassportImage: ImageView

    private fun processAndSetImage2(uri: Uri?) {
        uri?.let {
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, it)
            val scaledBitmap = Bitmap.createScaledBitmap(bitmap, 1000, 750, true)

            backPlasticPassportImage.setImageBitmap(scaledBitmap)
        }
    }


    private val getContent2 =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            backPlastic = uri
            processAndSetImage2(uri)
        }

////////////////////////////////////////////////////////////////////////////////////////////////////

    private lateinit var selfieWithPlasticPassportImage: ImageView

    private fun processAndSetImage3(uri: Uri?) {
        uri?.let {
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, it)
            val scaledBitmap = Bitmap.createScaledBitmap(bitmap, 1000, 750, true)

            selfieWithPlasticPassportImage.setImageBitmap(scaledBitmap)
        }
    }

    private val getContent3 =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            selfiePlastic = uri
            processAndSetImage3(uri)
        }
    ///////////////////////////////////////////////////////////////////////////////////////////////

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.plastic_photo_passport)

        ////////////////////////////////////////////////////////////////////////////////////////////////

        profileImageView = findViewById(R.id.profileImageView)
        uploadButton = findViewById(R.id.uploadButton)

        uploadButton.setOnClickListener {
            getContent.launch("image/*")
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////
        frontPlasticPassportImage = findViewById(R.id.image_1)
        backPlasticPassportImage = findViewById(R.id.image_2)
        selfieWithPlasticPassportImage = findViewById(R.id.image_3)

        frontPlasticPassportImage.setOnClickListener {
            getContent1.launch("image/*")
        }

        backPlasticPassportImage.setOnClickListener {
            getContent2.launch("image/*")
        }

        selfieWithPlasticPassportImage.setOnClickListener {
            getContent3.launch("image/*")
        }


        val doneButton = findViewById<Button>(R.id.done)
        doneButton.setOnClickListener {

            val id = intent.getIntExtra("idAmonatMobileClient", -1)

            if (profile != null && frontPlastic != null && backPlastic != null && selfiePlastic != null) {
                handleImageSelection(profile, id, 0)
                handleImageSelection(frontPlastic, id, 11)
                handleImageSelection(backPlastic, id, 12)
                handleImageSelection(selfiePlastic, id, 13)

                showToast("Загружено!")

                val intent = Intent(this@PlasticPhotoPassportPreview, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        else {
             showToast("Заполняйте все данные!")
             }
        }
    }

    private fun handleImageSelection(uri: Uri?, idAmonatMobileClient: Int, type: Int) {
        if (uri != null) {
            val inputStream = contentResolver.openInputStream(uri)
            val bytes = inputStream?.readBytes()
            inputStream?.close()

            if (bytes != null) {
                val photoFile = RequestBody.create(MediaType.parse("image/*"), bytes)
                val photoPart = MultipartBody.Part.createFormData("file", "photo.jpg", photoFile)

                val retrofit = Retrofit.Builder()
                    .baseUrl("http://192.168.21.252:8082/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val apiService = retrofit.create(ApiService::class.java)

                val call = apiService.uploadPhoto(photoPart, idAmonatMobileClient, type)

                call.enqueue(object : Callback<JSONStringer> {
                    override fun onResponse(call: Call<JSONStringer>, response: Response<JSONStringer>) {
                        if (response.isSuccessful) {
                            val jsonString = response.body()?.toString()
                            println(jsonString)
                        } else {
                            val errorBody = response.errorBody()?.string()
                            println(errorBody)
                        }
                    }

                    override fun onFailure(call: Call<JSONStringer>, t: Throwable) {
                        t.message?.let { Log.e("Retrofit Error", it) }
                    }

                })
            }
        }
    }

}