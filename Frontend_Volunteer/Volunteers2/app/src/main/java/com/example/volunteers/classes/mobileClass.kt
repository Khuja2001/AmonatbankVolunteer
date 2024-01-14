package com.example.volunteers.classes

import retrofit2.http.Multipart

data class AmonatMobileClient(
    val idVolunteer: Int,
    val nameAmonatMobileClient: String,
    val surnameAmonatMobileClient: String,
    val patronymicAmonatMobileClient: String?,
    val sexAmonatMobileClient: String,
    val phoneAmonatMobileClient: String,
    val tinAmonatMobileClient: String,
    val emailAmonatMobileClient: String?,
    val birthdayAmonatMobileClient: String
)

data class Images(
    val idAmonatMobileClient: Int,
    val type: Int,
    val photo: Multipart
)

data class InternetBankingClient(
    val idVolunteer: Int,
    val nameIBClient: String,
    val addressIBClient: String,
    val tinIBClient: String,
    val phoneNumDirectorIBClient: String,
    val phoneNumChiefAccountantIBClient: String
)

data class PosClient(
    val idVolunteer: Int,
    val namePosClient: String,
    val tinPosClient: String,
    val addressPosClient: String,
    val installAddressPosClient: String,
    val phoneNumPosClient: String
)

data class QrClient(
    val idVolunteer: Int,
    val nameQrClient: String,
    val tinQrClient: String,
    val addressQrClient: String,
    val ryamQrClient: String,
    val quoteQrClient: String,
    val phoneNumDirectorQrClient: String,
    val phoneNumChiefAccountantQrClient: String
)

data class SmsNotificationsClient(
    val idVolunteer: Int,
    val nameSmsNotificationsClient: String,
    val surnameSmsNotificationsClient: String,
    val patronymicSmsNotificationsClient: String,
    val phoneSmsNotificationsClient: String,
    val lastFourDigitCardSmsNotificationsClient: String,
    val commentsSmsNotificationsClient: String
)