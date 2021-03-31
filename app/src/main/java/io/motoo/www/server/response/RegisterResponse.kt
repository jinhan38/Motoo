package io.motoo.www.server.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RegisterResponse(
    val username: String = "",
    val password: String = "",
    val name: String = "",
    val nickname: String = "",
    val phoneNo: String = "",
    val gender: String = "",
    val birthday: String = "",
    val countryNo: String = "",
    val recommender: String = ""

) : Parcelable 