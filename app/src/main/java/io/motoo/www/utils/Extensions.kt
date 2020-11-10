package io.motoo.www.utils

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import com.google.android.material.tabs.TabLayout


private const val TAG = "Extensions"


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
fun TabLayout.tabLayoutController(completion: () -> Unit) {
    this.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) {
            Log.d(TAG, "onTabSelected: completion")
            completion()
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {


        }

        override fun onTabReselected(tab: TabLayout.Tab?) {

        }

    })

}
