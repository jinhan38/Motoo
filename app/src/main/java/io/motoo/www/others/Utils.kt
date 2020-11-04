package io.motoo.www.others

import android.app.Activity
import android.content.ContentValues
import android.util.Log
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import io.motoo.www.R
import java.util.*

class Utils {

    companion object {
        var fragmentStack: Stack<Fragment>? = null



        fun replaceFragment(fragment: Fragment, containerView : Int, activity: FragmentActivity){

//            var activity = fragment.activity
            var fragment = fragment
            Log.d(ContentValues.TAG, "replaceFragment: 진입 fragment : $fragment, containerView : $containerView")
            activity.supportFragmentManager?.commit {
                setCustomAnimations(
                    R.anim.slide_in,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.slide_out
                )
                replace(containerView, fragment)
                addToBackStack(null)
            }
        }
    }
}