package io.motoo.www.ui.login

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import io.motoo.www.R
import io.motoo.www.others.Utils
import io.motoo.www.others.onMyTextChanged
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.android.synthetic.main.fragment_password_reset.view.*

class PasswordResetFragment : Fragment() {

    lateinit var v: View

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_password_reset, container, false)


        v.password_reset_editText.onMyTextChanged {

            val colorStateListRed =
                ColorStateList.valueOf(resources.getColor(R.color.red, null))
            val colorStateListBlue =
                ColorStateList.valueOf(resources.getColor(R.color.primary_blue, null))

            if (it?.count()!! > 0) {

                if (Utils.isValidEmail(it.toString())) {
                    v.email_error_msg_pw_reset.visibility = View.GONE
                    ViewCompat.setBackgroundTintList(v.password_reset_editText, colorStateListBlue)
                    v.password_send_to_email_button.apply {
                        background = resources.getDrawable(R.drawable.bg_round_brand_4dp, null)
                        isEnabled = true
                    }


                } else {
                    v.email_error_msg_pw_reset.visibility = View.VISIBLE
                    ViewCompat.setBackgroundTintList(v.password_reset_editText, colorStateListRed)
                    v.password_send_to_email_button.apply {

                        background = resources.getDrawable(R.drawable.bg_round_gray_300_4dp, null)
                        isEnabled = true
                    }
                }
            }


        }
        return v
    }

}