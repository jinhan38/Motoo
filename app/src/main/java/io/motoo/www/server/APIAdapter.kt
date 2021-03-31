package io.motoo.www.server

import android.content.Context
import com.facebook.stetho.BuildConfig
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import io.motoo.www.others.Utils
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class APIAdapter {


    companion object {

        /**
         * Retrofit 객체를 초기화하는 메소드
         *
         * @param context
         * @param serviceName
         * @return
         */
        open fun retrofit(context: Context?, serviceName: Class<*>?): Any? {
            /**
             * OkHttpClient 객체 생성 과정
             *
             * 1. OkHttpClient 객체 생성
             * 2. 세션 데이터의 획득을 위해 response 데이터 중 헤더 영역의 쿠키 값을 가로채기 위한 RecivedCookiesInterceptor 추가
             * 3. 서버로 데이터를 보내기 전 세션 데이터 삽입을 위해 AddCookiesInterceptor 추가
             * 4. OkHttpClient 빌드
             *
             * 주의) 가로채기 위한 메소드는 addInterceptor이고 삽입하기 위한 메소드는 addNetworkInterceptor
             */
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new ReceivedCookiesInterceptor(context))
//                .addNetworkInterceptor(new AddCookiesInterceptor(context))
//                .readTimeout(60000, TimeUnit.SECONDS)
//                .connectTimeout(60000, TimeUnit.SECONDS)
//                .writeTimeout(60000, TimeUnit.SECONDS)
//                .build();
            if (BuildConfig.DEBUG) Stetho.initializeWithDefaults(context)
            val sthethoInterceptingClient = OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .readTimeout(60000, TimeUnit.SECONDS)
                .connectTimeout(60000, TimeUnit.SECONDS)
                .writeTimeout(60000, TimeUnit.SECONDS)
                .build()

            /**
             * Retrofit 객체 생성 과정
             *
             * 1. Retrofit 객체 생성
             * 2. base(api 서버) url 설정
             * 3. json 형식의 reponse 데이터의 파싱을 위해 Gson 추가
             * 3. 위에서 만든 OkHttpClient 객체를 추가
             * 4. Retrofit 빌드
             *
             * 주의) addConverterFactory를 추가하지 않을 경우 어플리케이션이 종료됨
             */
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(Utils.getBaseAPIUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .client(sthethoInterceptingClient)
                .build()
            /**
             * 서비스객체의 이름으로 Retrofit 객체 생성 및 반환
             *
             * ex) retrofit.create(SignService.class);
             */
            return retrofit.create(serviceName)
        }

    }
}