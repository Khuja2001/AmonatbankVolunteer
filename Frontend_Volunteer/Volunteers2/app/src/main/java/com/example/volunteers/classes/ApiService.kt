package com.example.volunteers.classes


import android.annotation.SuppressLint
import okhttp3.MultipartBody
import org.json.JSONStringer
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @POST("/amonatMobileClient/add")
    fun createAmonatMobileClient(@Body amonatMobileClient: AmonatMobileClient): Call<ApiResponse>

    @POST("/internetBankingClient/add")
    fun createInternetBankingClient(@Body internetBankingClient: InternetBankingClient): Call<JSONStringer>

    @POST("/posClient/add")
    fun createPosClient(@Body posClient: PosClient): Call<JSONStringer>

    @POST("/qrClient/add")
    fun createQrClient(@Body qrClient: QrClient): Call<JSONStringer>

    @POST("/smsNotificationsClient/add")
    fun createSmsNotificationsClient(@Body smsNotificationsClient: SmsNotificationsClient): Call<JSONStringer>

    @Multipart
    @POST("/upload/photo")
    fun uploadPhoto(
        @Part file: MultipartBody.Part?,
        @Part("idAmonatMobileClient") idAmonatMobileClient: Int,
        @Part("type") type: Int
    ): Call<JSONStringer>

    @POST("/volunteers/authenticate")
        fun authenticate(@Body request: AuthenticationRequest): Call<ApiResponseBool>

    class ApiResponse {
        private var message: String = ""
        private var id: Int = -1

        @SuppressLint("NotConstructor")
        fun ApiResponse(message: String, id: Int)
        {
            this.message = message
            this.id =id
        }
        fun getId(): Int {
            return id
        }
    }


    @SuppressLint("NotConstructor")
    class ApiResponseBool {

        private var condition: Boolean? = null
        private var nameSurname_volunteer: String? = ""
        private var id_volunteer: Int? = 0
        private var phone_volunteer: String = ""

        fun ApiResponseBool(condition: Boolean?, nameSurname_volunteer: String, id_volunteer: Int, phone_volunteer: String)
        {
            this.condition = condition;
            this.nameSurname_volunteer = nameSurname_volunteer;
            this.id_volunteer = id_volunteer
            this.phone_volunteer = phone_volunteer
        }

        fun getCondition(): Boolean? {
            return condition
        }

        fun setCondition(condition: Boolean?) {
            this.condition = condition
        }


        fun getNameSurname_volunteer(): String? {
            return nameSurname_volunteer
        }

        fun setNameSurname_volunteer(nameSurname_volunteer: String?) {
                this.nameSurname_volunteer = nameSurname_volunteer
        }


        fun getId_volunteer(): Int? {
            return id_volunteer
        }

        fun setId_volunteer(id_volunteer: Int?) {
            this.id_volunteer = id_volunteer
        }

        fun getPhone_volunteer(): String {
            return phone_volunteer
        }

        fun setPhone_volunteer(phone_volunteer: String) {
            this.phone_volunteer = phone_volunteer
        }
    }
}
