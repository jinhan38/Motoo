package io.motoo.www.server.register

import android.content.Context
import io.motoo.www.server.APIAdapter
import io.motoo.www.server.APIUrl
import io.motoo.www.server.response.EmailCheckResponse
import io.motoo.www.server.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

class SignUpAPI : APIAdapter() {


    companion object {

        fun getRetrofit(context: Context): ApplicationAPI {

            // 현재 서비스객체의 이름으로 Retrofit 객체를 초기화 하고 반환
            return (retrofit(context, ApplicationAPI::class.java) as ApplicationAPI?)!!
        }
    }

    interface ApplicationAPI {

        @FormUrlEncoded
        @POST(APIUrl.SIGN_UP)
        fun getRegisterAPI(
            @Field("username") username: String?,
            @Field("password") password: String?,
            @Field("name") name: String?,
            @Field("nickname") nickname: String?,
            @Field("phoneNo") phoneNo: String?,
            @Field("gender") gender: String?,
            @Field("birthday") birthday: String?,
            @Field("countryNo") countryNo: String?,
            @Field("recommender") recommender: String?
        ): Call<RegisterResponse?>?


        @FormUrlEncoded
        @GET(APIUrl.EMAIL_CHECK)
        fun getEmailCheckAPI(
            @Field("id") id: String?
        ): Call<EmailCheckResponse?>?

    }

}