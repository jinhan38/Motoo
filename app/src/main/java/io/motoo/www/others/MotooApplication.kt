package io.motoo.www.others

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import com.facebook.stetho.Stetho
import io.motoo.www.BuildConfig
import io.motoo.www.log.LogUtil
import io.motoo.www.service.RetrofitService

class MotooApplication : Application(), LifecycleObserver {

    lateinit var application: MotooApplication
    lateinit var retrofitService: RetrofitService

    override fun onCreate() {
        super.onCreate()

        //Stetho 초기화 및 observer를 달아줘야 chrome://inspect에서 인식 할 수 있음
        if (BuildConfig.DEBUG) Stetho.initializeWithDefaults(this)

        ProcessLifecycleOwner.get().lifecycle.addObserver(this)

        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(p0: Activity, p1: Bundle?) {
                LogUtil.d("onActivityCreated")
            }

            override fun onActivityStarted(p0: Activity) {
                LogUtil.d("onActivityStarted")
            }

            override fun onActivityResumed(p0: Activity) {
                LogUtil.d("onActivityResumed")
            }

            override fun onActivityPaused(p0: Activity) {
                LogUtil.d("onActivityPaused")
            }

            override fun onActivityStopped(p0: Activity) {
                LogUtil.d("onActivityStopped")
            }

            override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
                LogUtil.d("onActivitySaveInstanceState")
            }

            override fun onActivityDestroyed(p0: Activity) {
                LogUtil.d("onActivityDestroyed")
            }

        })

    }

    fun getMotooApplication(): MotooApplication {
        if (application == null) {
            throw IllegalStateException("This Application does not inherit com.kakao.GlobalApplication")
        }

        return application
    }


}