package io.motoo.www.ui.signUp

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import io.motoo.www.R
import io.motoo.www.log.LogUtil
import io.motoo.www.others.KeyboardVisibilityUtils
import io.motoo.www.others.Utils
import io.motoo.www.others.replaceFragment
import io.motoo.www.server.APIAdapter
import io.motoo.www.server.register.SignUpAPI
import io.motoo.www.server.response.EmailCheckResponse
import kotlinx.android.synthetic.main.fragment_sign_up_profile.view.*


class SignUpProfile : Fragment(), View.OnClickListener {
    companion object {
        private const val TAG = "SignUpFragment"
    }

    lateinit var v: View
    private lateinit var keyboardVisibilityUtils: KeyboardVisibilityUtils

    var isEmailValid: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_sign_up_profile, container, false)

        activity?.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        setupListener()

        return v

    }


    fun setupListener() {

        v.back_button.setOnClickListener(this)

        v.next.setOnClickListener(this)

        v.email_editText.addTextChangedListener(emailTextWatcher)

    }

    override fun onClick(v: View?) {

        when (v!!.id) {

            R.id.back_button -> findNavController().popBackStack()

            R.id.next -> {

                if (isEmailValid) {
                    findNavController().navigate(R.id.action_signUpFragment_to_verification)
                }

            }

        }
    }


    /**
     * ID = email editText watcher
     */
    private val emailTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {

            isEmailValid = Utils.isValidEmail(editable.toString())
            val idCheck = getEmailCheck(editable.toString())
            LogUtil.d("이메일 체크 : $idCheck")

            setValidCheck(
                v.tvEmailError, R.color.red,
                getString(R.string.email_valid_error), isEmailValid
            )

        }

    }

    fun getEmailCheck(email: String): String {

        val response =
            SignUpAPI.getRetrofit(requireContext()).getEmailCheckAPI(email) as EmailCheckResponse
        return response.useYn
    }


    /**
     * 유효성 체크에 따라 에러 텍스트뷰 컬러, 메세지 변경
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setValidCheck(
        errorTextView: TextView,
        color: Int,
        message: String,
        validCheck: Boolean
    ) {
        errorTextView.setTextColor(resources.getColor(color))
        errorTextView.text = message


        LogUtil.d("validCheck : " + validCheck)
        var drawable = if (validCheck) {
            resources.getDrawable(R.drawable.vaild_succes)
        } else {
            resources.getDrawable(R.drawable.ic_icon_16_form_delete_red)
        }
        LogUtil.d("validCheck : $validCheck , drawable : $drawable")
        errorTextView.setCompoundDrawables(drawable, null, null, null)


    }

}