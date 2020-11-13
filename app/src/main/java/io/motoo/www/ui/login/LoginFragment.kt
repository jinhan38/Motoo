package io.motoo.www.ui.login

import android.R.color
import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.navigation.fragment.findNavController
import io.motoo.www.R
import io.motoo.www.common.BaseFragment
import io.motoo.www.others.MyPasswordTransformationMethod
import io.motoo.www.others.Utils
import io.motoo.www.others.onMyTextChanged
import io.motoo.www.ui.Bottom
import kotlinx.android.synthetic.main.fragment_login.view.*


class LoginFragment : BaseFragment(), View.OnClickListener {

    companion object {
        private const val TAG = "LoginFragment"
    }

    var emailValidation = false
    var passwordValidation = false

    lateinit var v: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        v = inflater.inflate(R.layout.fragment_login, container, false)

        v.password_login.transformationMethod = MyPasswordTransformationMethod()

        setupListener()

        return v
    }

    override fun setupListener() {
        v.login_start_button.setOnClickListener(this)
        v.autoLogin_checkBox.setOnClickListener(this)

        v.email_address_login.onMyTextChanged {

            val colorStateListRed =
                ColorStateList.valueOf(resources.getColor(R.color.red, null))
            val colorStateListBlue =
                ColorStateList.valueOf(resources.getColor(R.color.primary_blue, null))

            Log.d(TAG, "setupListener:emailValidation : $emailValidation ")
            if (it?.count()!! > 0) {
                v.email_error_msg_login.visibility = View.VISIBLE

                if (Utils.isValidEmail(v.email_address_login.text.toString())) {
                    ViewCompat.setBackgroundTintList(v.email_address_login, colorStateListBlue)

                    v.email_error_msg_login.visibility = View.GONE
                    emailValidation = true


                } else {
                    ViewCompat.setBackgroundTintList(v.email_address_login, colorStateListRed)
                    v.email_error_msg_login.apply {
                        visibility = View.VISIBLE
                    }
                    emailValidation = false
                }

            } else {
                v.email_error_msg_login.visibility = View.GONE
                ViewCompat.setBackgroundTintList(v.email_address_login, colorStateListBlue)
            }

            loginButtonBgChange()
        }

        v.password_login.onMyTextChanged {
            passwordValidation = it?.count()!! > 4
            loginButtonBgChange()
        }

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun loginButtonBgChange() {
        if (emailValidation && passwordValidation) {
            v.login_start_button.apply {
                background = resources.getDrawable(R.drawable.bg_round_brand_4dp, null)
                isEnabled = true
            }
        } else {
            v.login_start_button.apply {
                background = resources.getDrawable(R.drawable.bg_round_gray_300_4dp, null)
                isEnabled = false
            }
        }

    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.login_start_button -> {
                startActivity(Intent(activity, Bottom::class.java))
            }
            R.id.autoLogin_checkBox -> {
                if (v.autoLogin_checkBox.isSelected) {
                    v.autoLogin_checkBox.isSelected = false
                } else {
                    v.autoLogin_checkBox.isSelected = true
                }

            }
        }

    }
}