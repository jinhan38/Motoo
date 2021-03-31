package io.motoo.www.server.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EmailCheckResponse(
    val useYn: String = "",
    val createdDateTime: String = "",
    val updatedDateTime: String = "",
    val id: String = "",
    val username: String = "",
    val password: String = "",
    val name: String = "",
    val nickname: String = "",
    val phoneNo: String = "",
    val gender: String = "",
    val birthday: String = "",
    val countryNo: String = "",
    val recommender: String = "",
    val profileImg: String = "",
    val profileImgFile: String = "",
    val lastLoginDateTime: String = "",
    val accountStatus: String = "",
    val deletedAccountDateTime: String = ""
) : Parcelable