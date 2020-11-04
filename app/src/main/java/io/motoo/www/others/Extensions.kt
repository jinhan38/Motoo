package io.motoo.www.others

import android.content.ContentValues.TAG
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import io.motoo.www.R


//EditText 익스텐션 메소드
//afterTextChanged만 쓰려고 한다
fun EditText.onMyTextChanged(completion: (Editable?) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {

        override fun afterTextChanged(editable: Editable?) {
            completion(editable)
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }


    })

}

fun Fragment.replaceFragment(containerView: Int, activity: FragmentActivity) {

    var fragment = this
    Log.d(TAG, "replaceFragment: 진입 fragment : $fragment, containerView : $containerView")
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
