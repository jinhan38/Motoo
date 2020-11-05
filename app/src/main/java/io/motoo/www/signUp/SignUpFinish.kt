package io.motoo.www.signUp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import io.motoo.www.R
import io.motoo.www.customInterface.BackButton
import io.motoo.www.others.replaceFragment
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.android.synthetic.main.fragment_sign_up.view.*

class SignUpFinish : Fragment() {

    lateinit var v: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        activity?.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        v = inflater.inflate(R.layout.fragment_sign_up_finish, container, false)

        v.back_button.setOnClickListener {
            activity?.onBackPressed()
        }
        return v
    }


}